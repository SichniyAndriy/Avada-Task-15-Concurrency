package avada.course.question_07._02;

/*
*  Создать коллекцию с именами пользователей.
*  Во время работы программы запросить ввод числа из консоли.
*  Создать поток1, который должен с задержкой в 5 секунд вернуть имя пользователя под введенным номером.
*  Создать поток2, который будет потоком-демоном и будет имитировать загрузку в виде вывода "."
*  в консоль во время работы потока 1. После окончания работы потока1, вывести полученное имя в консоль.
*  Используйте интерфейс Callable в потоке 1. То есть в процессе работы программы, после ввода числа из консоли,
*  главный поток вызывает поток1 и ждет возврата его данных. В это время поток2 выводит в консоль “.”
*  как визуализацию загрузки.
*  Программа заканчивает свою работу, когда поток1 возвращает данные и эти данные будут отображены в консоли.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import net.datafaker.Faker;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> nameList = initNameList();
        System.out.print("Введіть номер користувача(1 - 25): ");
        Scanner scanner = new Scanner(System.in);
        int nextInt = scanner.nextInt();

        Thread thread = new Thread(new Daemon());
        thread.setDaemon(true);
        Future<String> future = Executors.newSingleThreadExecutor().submit(new Thread1(nameList, nextInt - 1));
        thread.start();

        System.out.println("\n" + future.get());
        System.exit(0);
    }

    private static List<String> initNameList() {
        List<String> list = new ArrayList<>();
        Faker faker = new Faker(Locale.getDefault());
        for (int i = 0; i < 25; ++i) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            list.add(firstName.concat(" ").concat(lastName));
        }
        return list;
    }
}
