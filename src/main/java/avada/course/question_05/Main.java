package avada.course.question_05;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// 5. Для чего нужны атомарные типы данных? Привести прикладные примеры

public class Main {
    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        AtomicInteger atomicInteger = new AtomicInteger();
        AtomicLong atomicLong = new AtomicLong();

        atomicBoolean.set(false);
        atomicBoolean.set(true);

        atomicInteger.incrementAndGet();
        atomicInteger.decrementAndGet();
        atomicInteger.addAndGet(5);

        atomicLong.addAndGet(10);
        atomicLong.decrementAndGet();
        atomicLong.incrementAndGet();
    }
}
