package com.redis.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangxiao on 17/六月/1.
 */
public class TestAtomIntegerOperation implements Runnable {
    private AtomicInteger atomicInteger;

    private String name;

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
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
            int atom = atomicInteger.incrementAndGet();
            System.out.println("name:  " + this.name + " *** currentValue:  " + atom);
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        TestAtomIntegerOperation demo1 = TestAtomIntegerOperation.class.newInstance();
        TestAtomIntegerOperation demo2 = TestAtomIntegerOperation.class.newInstance();
        demo1.setName("demo1");
        demo2.setName("demo2");
        demo1.setAtomicInteger(atomicInteger);
        demo2.setAtomicInteger(atomicInteger);
        Thread t1 = new Thread(demo1);
        Thread t2 = new Thread(demo2);
        t1.start();
        t2.start();
        while (true){
           if(t1.getState() == Thread.State.TERMINATED && t2.getState() == Thread.State.TERMINATED){
               System.out.println("***************************");
               System.out.println(atomicInteger.intValue());
               break;
           }
        }

    }
}

