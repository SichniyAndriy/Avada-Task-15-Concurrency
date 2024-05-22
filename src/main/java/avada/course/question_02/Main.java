package avada.course.question_02;

// 2. Привести примеры способов остановки потоков

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Runnable myRunnable = () -> {
            System.out.print("Do some work ");
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(300);
                    for (int i = 0; i < 3; ++i) {
                        System.out.print('.');
                        Thread.sleep(300);
                    }
                    System.out.print("\b\b\b");
                }
            } catch (InterruptedException e) {
                System.out.println("\nThread execution interrupted");
            }
        };

        Thread thread = Executors.defaultThreadFactory().newThread(myRunnable);
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread.interrupt();

        Runnable myAnotherRunnable = () -> {
            System.out.println("Do another work");
            try {
                while (true) {
                    if(Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException("Some thread exhibited flag");
                    }
                    System.out.println("Yes, I do something");
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        };

        Thread thread2 = Executors.defaultThreadFactory().newThread(myAnotherRunnable);
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread2.interrupt();
    }
}
