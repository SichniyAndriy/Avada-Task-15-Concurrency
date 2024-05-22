package avada.course.quesrion_03;

import java.util.concurrent.Executors;

// 3. Привести пример создания фонового потока (поток демона)

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            System.out.print("Do some work");
            try {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; ++j) {
                        Thread.sleep(300);
                        System.out.print('.');
                    }
                    Thread.sleep(300);
                    System.out.print("\b\b\b");
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        };

        Thread thread = Executors.defaultThreadFactory().newThread(runnable);
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(3000);
    }
}
