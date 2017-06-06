/**
 * Created by wangxiao on 16/五月/18.
 */
public class HelloWorld extends Thread {
    private int age;
    private String name;
    private Thread thread;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    // test 资源相互测试
    public static void main(String[] args) throws InterruptedException {
        HelloWorld thread1 = new HelloWorld();
        HelloWorld thread2 = new HelloWorld();
        String name = thread1.getState().name();
        System.out.println(name);
        thread1.setName("T-thread1");
        thread2.setName("T-thread2");
        thread1.setThread(thread2);
        thread1.start();
        thread2.setThread(thread1);
        thread2.start();
        //System.out.println(Thread.currentThread().getName());
        //thread1.run();
        // System.out.println(thread1.getName());
        //thread2.run();
        //System.out.println(thread1.getName());
    }


    private int anInt;


    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        String currentThreadName = currentThread.getName();
        try {
            synchronized (HelloWorld.class) {
                while (true) {
                    Thread.sleep(1000);
                    System.out.println("*******************************");
                    System.out.println("currrentThread:  " + currentThreadName );
                    System.out.println("currrentThread state: " + currentThread.getState().name());
                    System.out.println("ownThread:  " + thread.getName() );
                    System.out.println("ownThread state: " + thread.getState().name());
                    System.out.println("*******************************");
                    System.out.println("");
                    HelloWorld.class.notify();
                    HelloWorld.class.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
