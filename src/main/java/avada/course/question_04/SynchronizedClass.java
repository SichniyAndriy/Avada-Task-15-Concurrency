package avada.course.question_04;

public class SynchronizedClass {
    private String line;
    private int num = 0;
    private Object object = new Object();

    public synchronized void method() {
        System.out.println("Do work");
    }

    public void anotherMethod() {
        synchronized (line) {
            line = modify(line);
        }

        synchronized (object) {
            ++num;
        }
    }

    private String modify(String line) {
        return "new" + line;
    }
}
