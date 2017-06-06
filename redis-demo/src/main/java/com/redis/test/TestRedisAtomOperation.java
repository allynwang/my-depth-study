package com.redis.test;

import com.redis.RedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by wangxiao on 17/六月/1.
 */
public class TestRedisAtomOperation implements Runnable {

    private RedisClientTemplate redisClientTemplate;

    private String name;

    public RedisClientTemplate getRedisClientTemplate() {
        return redisClientTemplate;
    }

    public void setRedisClientTemplate(RedisClientTemplate redisClientTemplate) {
        this.redisClientTemplate = redisClientTemplate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int i = 0 ; i<10000 ; i++){
            Long atom = redisClientTemplate.incr("atom");
            //String atom = redisClientTemplate.get("atom");
            System.out.println("name:  " + this.getName() +"atom:  " + atom);
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ApplicationContext ac  = new ClassPathXmlApplicationContext("spring-context.xml","applicationContext-redis.xml");
        RedisClientTemplate redisClientTemplate = (RedisClientTemplate) ac.getBean("redisClientTemplate");
        RedisClientTemplate bean = ac.getBean("redisClientTemplate",RedisClientTemplate.class);
        String message = redisClientTemplate.set("atom", "0");
        TestRedisAtomOperation demo1 = TestRedisAtomOperation.class.newInstance();
        TestRedisAtomOperation demo2 = TestRedisAtomOperation.class.newInstance();
        demo1.setName("demo1");
        demo2.setName("demo2");
        demo1.setRedisClientTemplate(redisClientTemplate);
        demo2.setRedisClientTemplate(redisClientTemplate);
        Thread t1 = new Thread(demo1);
        Thread t2 = new Thread(demo2);
        t1.start();
        t2.start();
        while (true){
           if(t1.getState() == Thread.State.TERMINATED && t2.getState() == Thread.State.TERMINATED){
               String atom = redisClientTemplate.get("atom");
               System.out.println(atom);
               break;
           }
        }

    }
}
