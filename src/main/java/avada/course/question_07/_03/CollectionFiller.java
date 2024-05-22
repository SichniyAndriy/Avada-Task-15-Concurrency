package avada.course.question_07._03;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CollectionFiller implements Runnable {
    private List<Integer> collection;
    private final int size;

    public CollectionFiller(List<Integer> list, int size) {
        collection = list;
        this.size = size;
    }

    @Override
    public void run() {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        for (int i = 0; i < size; ++i) {
            collection.add(localRandom.nextInt());
        }
    }
}
