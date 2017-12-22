package com.java1234.lucha.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SpringUtil {
	
	private static final ApplicationContext act;
	static{
		act = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	public static <T> T getObject(String name,Class<T> t){
		return act.getBean(name,t);
	}
	
	public static void main(String[] args) {
		JedisPool jedisPool = SpringUtil.getObject("jedisPool", JedisPool.class);
		Jedis jedis = jedisPool.getResource();
		String ping = jedis.ping();
		System.out.println(ping);
	}

}
