package avada.course.question_01;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Message from MyThread class - extended Thread");
    }
}
