package com.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangxiao on 17/五月/25.
 */
public class RedisMain {
    public static void main(String[] args) {
        ApplicationContext ac  = new ClassPathXmlApplicationContext("spring-context.xml","applicationContext-redis.xml");
        RedisClientTemplate redisClientTemplate = (RedisClientTemplate) ac.getBean("redisClientTemplate");
        //String message = redisClientTemplate.set("atom", "1");
        String atom = redisClientTemplate.get("atom");
        System.out.println(atom);


    }
}
