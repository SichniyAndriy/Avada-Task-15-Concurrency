package avada.course.question_06;

// 6. Что такое функциональный интерфейс? Привести прикладной пример.

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Runnable");
        }
    };

    Callable callable = new Callable() {
        @Override
        public Object call() throws Exception {
            return "Callable";
        }
    };

    Function<Integer, Double> function = new Function<Integer, Double>() {
        @Override
        public Double apply(Integer integer) {
            return integer.doubleValue();
        }
    };

    Consumer<String> consumer = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println(s);
        }
    };

    Supplier<String> supplier = new Supplier<String>() {
        @Override
        public String get() {
            return "new line";
        }
    };

    Predicate<Integer> predicate = new Predicate<Integer>() {
        @Override
        public boolean test(Integer integer) {
            return integer % 2 == 0;
        }
    };
}
