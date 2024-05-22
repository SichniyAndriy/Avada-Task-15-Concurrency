package avada.course.question_07._04;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class RacingCar implements Callable<RacingCar> {
    public final int number;
    private final CyclicBarrier barrier;
    private long result = 0;

    private RacingCar(int number, CyclicBarrier barrier) {
        this.number = number;
        this.barrier = barrier;
    }

    static RacingCar of(int number, CyclicBarrier barrier) {
        return new RacingCar(number, barrier);
    }

    @Override
    public RacingCar call() throws Exception {
        System.out.println("Гонка № " + number + " на стартовій лінії");
        barrier.await();

        long startTime = System.currentTimeMillis();
        System.out.println("Гонка № " + number + " стартувала");

        Thread.sleep(ThreadLocalRandom.current().nextLong(3000, 3500));

        System.out.println("Гонка № " + number + " перед тунелем");
        Main.tunnel.passingTunnel(number);
        System.out.println("Гонка № " + number + " виїхала із тунеля");

        Thread.sleep(ThreadLocalRandom.current().nextLong(3000, 3500));

        System.out.println("Гонка № " + number + " на фініші");
        result = System.currentTimeMillis() - startTime;
        barrier.await();
        return this;
    }

    public int getNumber() {
        return number;
    }

    public long getResult() {
        return result;
    }
}
