package com.java1234.lucha.service;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.java1234.lucha.util.Article;
import com.java1234.lucha.util.BeanUtils;
import com.java1234.lucha.util.RedisKey;
import com.java1234.lucha.util.RedisUtil;
import com.java1234.lucha.util.User;

public class ArticleService {
	
	public static final int VOTE_LIMIT_TIMT = 7 * 86400;
	
	/**
	 * 按照发布日期获取文章列表
	 */
	public String getArtsByPostTime(){
		int start = 0;
		int end = -1;
		JSONArray jsonArray = new JSONArray();
		Set<String> ids = RedisUtil.getArtIdsOrderBy(RedisKey.ART_POST_TIME, start, end);
		for(String id : ids){
			Map<String, String> artMap = RedisUtil.hgetAll(RedisKey.ART_KEY + id);
			jsonArray.add(artMap);
		}
		String result = jsonArray.toJSONString();
		return result;
	}
	
	/**
	 * 按照评分高低获取文章列表
	 */
	public String getArtsByScore(){
		int start = 0;
		int end = -1;
		JSONArray jsonArray = new JSONArray();
		Set<String> ids = RedisUtil.getArtIdsOrderBy(RedisKey.ART_SCORE, start, end);
		for(String id : ids){
			Map<String, String> artMap = RedisUtil.hgetAll(RedisKey.ART_KEY + id);
			jsonArray.add(artMap);
		}
		String result = jsonArray.toJSONString();
		return result;
	}

	/**
	 * 对文章进行投票
	 * @param art
	 * @param user
	 */
	public void voteArticle(Article art, User user) {
		long systime = new Date().getTime() / 1000;
		Double post = RedisUtil.getScore(RedisKey.ART_POST_TIME, String.valueOf(art.getId()));
		if (post == null || (systime - post) > VOTE_LIMIT_TIMT) {
			// 文章超出投票截止日期
			return;
		}
		Long voted = RedisUtil.sadd(RedisKey.ART_VOTE_USER + art.getId(), String.valueOf(user.getId()));
		if (voted == 0) {
			return ;//改用户已投票该文章
		}
		//文章增加分数
		RedisUtil.zincrby(RedisKey.ART_SCORE, Double.valueOf(432), String.valueOf(art.getId()));
		//文章增加投票数
		RedisUtil.hincrby(RedisKey.ART_KEY + art.getId(),"votes" , 1);
	}
	
	/**
	 * 发布文章
	 * @param art
	 * @param user
	 */
	public void postArt(Article art, User user){
		Long incr = RedisUtil.incr(RedisKey.ART_ID);
		art.setId(incr);
		Long time = System.currentTimeMillis() / 1000;
		art.setPostTime(time);
		RedisUtil.sadd(RedisKey.ART_VOTE_USER + art.getId(), String.valueOf(user.getId()));
		//设置失效时间
		RedisUtil.setExpire(RedisKey.ART_VOTE_USER + art.getId(), VOTE_LIMIT_TIMT);
		//将文章存入hash
		Map<String, String> map = BeanUtils.object2Map(art);
		RedisUtil.hmset(RedisKey.ART_KEY + incr, map);
		//将文章添加到按照评分和时间排序的有序集合中
		RedisUtil.zadd(RedisKey.ART_SCORE, Double.valueOf(432), String.valueOf(incr));
		RedisUtil.zadd(RedisKey.ART_POST_TIME, Double.valueOf(time), String.valueOf(incr));
	}

}
