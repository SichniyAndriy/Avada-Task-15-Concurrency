package avada.course.question_07._02;

import java.util.List;
import java.util.concurrent.Callable;

public class Thread1 implements Callable<String> {
    private List<String> list;
    private int index;

    public Thread1(List<String> list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return list.get(index);
    }
}
