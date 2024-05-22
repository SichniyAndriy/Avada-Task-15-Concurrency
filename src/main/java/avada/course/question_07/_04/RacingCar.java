package avada.course.question_07._04;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

public class RacingCar implements Callable<RacingCar> {
    @Getter
    public final int number;
    @Getter
    private long result = 0;

    private final CyclicBarrier barrier;
    private final CountDownLatch latch;

    private RacingCar(int number, CyclicBarrier barrier, CountDownLatch latch) {
        this.number = number;
        this.barrier = barrier;
        this.latch = latch;
    }

    static RacingCar of(int number, CyclicBarrier barrier, CountDownLatch latch) {
        return new RacingCar(number, barrier, latch);
    }

    @Override
    public RacingCar call() throws Exception {
        System.out.println("Гонка № " + number + " на стартовій лінії");
        barrier.await();
        latch.await();

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
}
