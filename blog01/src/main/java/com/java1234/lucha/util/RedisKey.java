package com.java1234.lucha.util;

public class RedisKey {
	
	/**
	 * 文章评论时间有序集合
	 */
	public static final String ART_POST_TIME = "acticle:post:time";
	/**
	 * 文章分数有序集合
	 */
	public static final String ART_SCORE = "arcticle:score";
	/**
	 * 文章散列
	 */
	public static final String ART_KEY = "article:hash:";
	
	/**
	 * 文章id计数器
	 */
	public static final String ART_ID = "article:id";
	
	/**
	 * 文章投票的用户集合
	 */
	public static final String ART_VOTE_USER = "article:vote:user:";

}
