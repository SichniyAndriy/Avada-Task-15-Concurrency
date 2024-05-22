package avada.course.question_07._03;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;

/*
* В главном потоке создать и вызвать два одинаковых потока, которые заполнят коллекцию типа List любыми числами.
* Каждый поток должен добавить в коллекцию по 10000 элементов. В конце главного потока дождаться завершения
* вышеуказанных двух потоков и затем вывести размер коллекции. Всего в коллекции должно быть 20000 элементов.
*/

public class Main {
    public static void main(String[] args) {
        final int size = 10_000;
        List<Integer> collection = new CopyOnWriteArrayList<>();

        Runnable filler1 = new CollectionFiller(collection, size);
        Runnable filler2 = new CollectionFiller(collection, size);

        Thread thread1 = Executors.defaultThreadFactory().newThread(filler1);
        Thread thread2 = Executors.defaultThreadFactory().newThread(filler2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Розмір колекції: " + collection.size());
    }
}
