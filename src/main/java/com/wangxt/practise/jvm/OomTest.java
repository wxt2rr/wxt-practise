package com.wangxt.practise.jvm;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class OomTest {
    static Executor executor = Executors.newFixedThreadPool(3);//此处关键点不是说的线程池问题
    static CompletionService<String> service = new ExecutorCompletionService<>(executor);
    // 我们这个不需要返回值了，DB这边也不需要更新了。因为我们直接在我们的异步线程run方法里进行我们的异常的catch以及log记录。
    // updateDBStatus();性能问题。所以我们采取了这种方式，如果有异常，就重试。
    public static void test() throws InterruptedException, ExecutionException {
        service.submit(() -> "Successfully!--" + Thread.currentThread().getName());
        //上线 5 天后，发生了 OOM 异常。因为一直有返回值的积累，无法清除。
        //========35220Exception in thread "main" java.lang.OutOfMemoryError
        // 内存泄露的点在于：我们的返回值实际上以及不再使用了，但是仍然被 static service 中的 LinkedBq所引用。
    }

    private static void updateDBStatus() {
        //更新DB执行状态。
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
            for(int i=0;i<45000;i++) {
                System.out.println("========" + i);
                test();
            }
            Thread.sleep(10000000);
    }
}

// ExecutorCompletionService 什么场景下使用呢？ 首先回忆一下，我们接触的 ExecutorService， ExecutorCompletionService有什么
// 区别？
// ExecutorCompletionService： 当我们需要获取每一个线程执行的返回值的时候，使用ExecutorCompletionService
// 问： ExecutorService中我们说过Future返回值，futrue.get()。
//  ExecutorService + Future
// List.add(future); 10个返回值; 一个返回值对应一个线程执行；10个返回值代表10个。如果有9个返回值1s返回，剩下的一个100s
// for(List) {
//   future.get() 阻塞第10个返回值直到 100 s
//  }
// 问： ExecutorService + Future 那就不要用了，不是有问题吗？
// 答：10个返回值必须全部执行完毕之后，才能继续向下执行，这个时候我们的这个组合就很有用了。

// ExecutorCompletionService 这个东西是，有10个异步线程，他不管谁快谁慢，都会将这个10个返回值存储到队列里。调用端（我们）
// 想取出来随时就能取出来。
// 问： 校长，这种跟 future有啥区别啊？ -- future的get是自身的，ExecutorCompletionService返回值放队列里边了。

