package avada.course.question_01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import lombok.SneakyThrows;

// 1. Привести примеры способов создания потоков

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        new MyThread().start();

        Runnable myRunnable = () -> System.out.println("Message from MyRunnable class - implemented Runnable");
        new Thread(myRunnable).start();

        ThreadFactory threadFactory = Thread::new;
        Runnable myAnotherRunnable =
                () -> System.out.println(
                        "Message from another MyRunnable class - implemented Runnable and created bu factory"
                );
        threadFactory.newThread(myAnotherRunnable);

        Callable<String> myCallable = () ->  "Message from another MyCallable class - implemented Callable";
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(myCallable);
        executorService.shutdown();
        System.out.println(submit.get());
    }
}
