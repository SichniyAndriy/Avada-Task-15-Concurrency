package avada.course.question_07._04;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import net.datafaker.Faker;

public class Main {
    public final static Tunnel tunnel = new Tunnel();

    private final static int CARS_IN_RACE = 10;
    private final static Faker FAKER = new Faker(Locale.getDefault());

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_IN_RACE + 1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(CARS_IN_RACE);
        List<RacingCar> racingCars = IntStream.range(1, 11).mapToObj(x -> RacingCar.of(x, cyclicBarrier)).toList();
        List<Future<RacingCar>> futures = new ArrayList<>();
        for (RacingCar car: racingCars) {
            ScheduledFuture<RacingCar> future =
                    scheduledExecutorService.schedule(
                            car,
                            ThreadLocalRandom.current().nextInt(3000, 5000),
                            TimeUnit.MILLISECONDS
                    );
            futures.add(future);
        }
        scheduledExecutorService.shutdown();

        System.out.println("Машини під'їжають до стартової лінії");
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        cyclicBarrier.reset();
        System.out.println("\n---СТАРТ---");

        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        Map<Long, Integer> results = new TreeMap<>();
        for (Future<RacingCar> future: futures) {
            try {
                results.put(future.get().getResult(), future.get().getNumber());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\n--- Результат перегонів ---\n");
        for (var result: results.entrySet()) {
            double secTime = result.getKey() / 1000.;
            System.out.printf("Гонка № %2d ", result.getValue());
            for (int i = 0; i < (int) secTime; ++i) {
                System.out.print('-');
            }
            System.out.println(" " + secTime + " c." );
        }
    }
}
