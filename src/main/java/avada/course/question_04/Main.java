package avada.course.question_04;

// 4. Привести примеры синхронизации потоков и потокобезопасных коллекций

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {
        List<Integer> concurrencyList = new CopyOnWriteArrayList<>();
        concurrencyList.add(1);
        concurrencyList.add(2);
        concurrencyList.add(3);

        ConcurrentMap concurrentMap = new ConcurrentHashMap();
        concurrentMap.put("one", 1);
        concurrentMap.put("two", 2);
        concurrentMap.put("three", 3);


        List<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
    }
}
