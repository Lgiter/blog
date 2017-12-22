package com.java1234.lucha.util;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {
	
	private static final JedisPool redisPool;
	
	static{
		redisPool = SpringUtil.getObject("jedisPool", JedisPool.class);
	}
	
	private static Jedis getJedis(){
		return redisPool.getResource();
	}
	
	public static Set<String> getArtIdsOrderBy(String key,int start, int end){
		Jedis jedis = getJedis();
		try {
			return jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			return null;
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * 设置redis key的过期时间
	 * @param key
	 * @param value
	 */
	public static void setExpire(String key , int seconds){
		Jedis jedis = getJedis();
		try {
			jedis.expire(key, seconds);
		} catch (Exception e) {
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * 获取hash
	 * @param key
	 * @return
	 */
	public static Map<String, String>  hgetAll (String key){
		Jedis jedis = getJedis();
		try {
			return jedis.hgetAll(key);
		} catch (Exception e) {
			return null;
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * 向有序集合中添加元素
	 * @param key
	 * @param score
	 * @param member
	 */
	public static void zadd(String key,Double score, String member){
		Jedis jedis = getJedis();
		try {
			jedis.zadd(key, score, member);
		} catch (Exception e) {
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * 将hash存入redis
	 * @param key
	 * @param map
	 */
	public static void hmset(String key, Map<String,String> map){
		Jedis jedis = getJedis();
		try {
			String hmset = jedis.hmset(key, map);
		} catch (Exception e) {
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * redis 计数器
	 * @param key
	 * @return
	 */
	public static Long incr(String key){
		Jedis jedis = getJedis();
		try {
			return jedis.incr(key);
		} catch (Exception e) {
			return null;
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * 散列中的某个属性 自增value
	 * @param key
	 * @param field
	 * @param value
	 */
	public static void hincrby(String key,String field,int value){
		Jedis jedis = getJedis();
		try {
			jedis.hincrBy(key, field, value);
		} catch (Exception e) {
		}finally {
			jedis.close();
		}
	}
	/**
	 * 指定成员增加分值
	 * @param key
	 * @param score
	 * @param member
	 */
	public static void zincrby(String key,Double score,String member){
		Jedis jedis = getJedis();
		try {
			jedis.zincrby(key, score, member);
		} catch (Exception e) {
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * 获取指定成员的分值
	 * @param key
	 * @param member
	 * @return
	 */
	public static Double getScore(String key, String member){
		Jedis jedis = getJedis();
		try {
			return jedis.zscore(key, member);
		} catch (Exception e) {
			return -1.0;
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * 向集合中添加成员
	 * @param key
	 * @param members
	 * @return
	 */
	public static Long sadd(String key,String... members){
		Jedis jedis = getJedis();
		try {
			Long sadd = jedis.sadd(key, members);
			return sadd;
		} catch (Exception e) {
			return 0L;
		}finally {
			jedis.close();
		}
	}
	
	public static void main(String[] args) {
		Double score = getScore("aaa","aaa");
		System.out.println(score);
	}

}
