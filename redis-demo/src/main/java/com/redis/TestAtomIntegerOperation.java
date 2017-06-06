package com.redis;

import com.redis.test.AtomInteger;

/**
 * Created by wangxiao on 17/六月/1.
 */
public class TestAtomIntegerOperation implements Runnable {
    private AtomInteger atomInteger;

    public AtomInteger getAtomInteger() {
        return atomInteger;
    }

    public void setAtomInteger(AtomInteger atomInteger) {
        this.atomInteger = atomInteger;
    }

    @Override
    public void run() {
        for(int i = 0 ; i<10000 ; i++){
            Integer atom = this.atomInteger.getAtom();
            atom+=1;
            atomInteger.setAtom(atom);
            System.out.println(this.atomInteger.getAtom().intValue());
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        AtomInteger atomInteger = AtomInteger.class.newInstance();
        atomInteger.setAtom(new Integer(0));
        TestAtomIntegerOperation demo1 = TestAtomIntegerOperation.class.newInstance();
        TestAtomIntegerOperation demo2 = TestAtomIntegerOperation.class.newInstance();
        demo1.setAtomInteger(atomInteger);
        demo2.setAtomInteger(atomInteger);
        Thread t1 = new Thread(demo1);
        Thread t2 = new Thread(demo2);
        t1.start();
        t2.start();
        while (true){
           if(t1.getState() == Thread.State.TERMINATED && t2.getState() == Thread.State.TERMINATED){
               System.out.println(atomInteger.getAtom());
               break;
           }
        }

    }
}

