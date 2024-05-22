package avada.course.question_07._01;

/*
* Создать 3 потока, каждый из них выводит 5 раз одну букву.
* Первый поток выводи 5 раз букву "А", второй 5 раз "В", третий 5 раз "С".
* Настроить работу потоков так, чтобы после запуска всех 3-х потоков в консоли было "АВСАВСАВСАВСАВС".
* То есть 1-ый поток печатает свою букву, потом второй свою, потом третий свою и так,
* пока не будет выведена "АВС" 5 раз.
* Используйте для этого синхронизацию потоков по монитору и методы для остановки и пробуждения потоков.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {
    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            LetterPrinter letterPrinter = new LetterPrinter((char) ('A' + i), 5, 7 >> i);
            Thread thread = threadFactory.newThread(letterPrinter);
            threadList.add(thread);
        }
        threadList.forEach(Thread::start);
        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println();
    }
}
