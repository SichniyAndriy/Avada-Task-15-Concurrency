package avada.course.question_04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockClass {
    private String line;
    private int num = 0;
    private Object object = new Object();
    private Lock lock = new ReentrantLock(true);

    public void method() {
        lock.lock();
        System.out.println("Do work");
        lock.unlock();
    }

    public void anotherMethod() {
        lock.lock();
            line = modify(line);
        lock.unlock();
        lock.lock();
        ++num;
        lock.unlock();
    }

    private String modify(String line) {
        return "new" + line;
    }
}
