package fun.enhui.thread.base;

import java.util.concurrent.*;

/**
 * 创建线程的4种方式
 * 1.继承 Thread 类
 * 2.实现 Runnable 接口
 * 3.实现 Callable 接口
 * 4.线程池
 *
 * @Author 胡恩会
 * @Date 2020/6/28 20:45
 **/
public class T01_CreatThread {
    public static void main(String[] args) {
        // 1.继承 Thread 方式
        MyThread thread1 = new MyThread();
        thread1.start();

        // 2.实现 Runnable 方式
        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();

        // 3.实现 Callable 方式
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread thread3 = new Thread(futureTask);
        thread3.start();
        try {
            String result = futureTask.get();
            System.out.println("Callable 方式返回值：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 4. 线程池
        // 自定义线程池，共 7 个参数
        // 1.核心线程数 2.最大线程数 3.生存时间 4.生存时间的单位
        // 5.任务队列 6.线程工厂 7.拒绝策略
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 10; i++) {
            pool.execute(()->{
                System.out.println(Thread.currentThread().getName() + "线程池执行任务");
            });
        }

    }
}

/**
 * 1. 继承 Thread 类
 * 2. 重写 run()
 *
 * @Author: 胡恩会
 * @Date: 2020/6/28 20:47
 **/
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("继承 Thread 类创建线程");
    }
}

/**
 * 1. 实现 Runnable 接口
 * 2. 重写 run()
 *
 * @Author: 胡恩会
 * @Date: 2020/6/28 20:50
 **/
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("实现 Runnable 接口创建线程");
    }
}

/**
 * 1.实现 Callable 接口
 * 2.重写 call()
 *
 * @Author: 胡恩会
 * @Date: 2020/6/28 22:10
 **/
class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("实现 Callable 接口创建线程");
        return "我是返回值";
    }
}
