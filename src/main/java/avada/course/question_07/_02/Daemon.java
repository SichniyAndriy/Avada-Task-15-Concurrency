package avada.course.question_07._02;

public class Daemon implements Runnable {
    @Override
    public void run() {
        System.out.print("Пошук");
        try {
            while (true) {
                Thread.sleep(300);
                for (int i = 0; i < 3; ++i) {
                    System.out.print('.');
                    Thread.sleep(300);
                }
                System.out.print("\b\b\b");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
