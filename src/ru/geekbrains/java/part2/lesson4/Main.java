package ru.geekbrains.java.part2.lesson4;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 1. Создать коллекцию типа List. Наполнить ее значениями и вывести значения в консолько при помощи ее метода forEach.
 * <p>
 * 2. Создать утилитарный метод forItem. Метод принимает два параметра: Коллекция Set<String>
 * и консьюмер типа Consumer<String>. Внутри метода проитерироваться по коллекции
 * и для каждого элемента выполнить метод консьюмера accept, который выводит значение элемента в консоль.
 * <p>
 * 3. Создать утилитарный метод doubleUp. Метод принимает два параметра:
 * значение типа int и консьюмер типа Supplier<Integer>. Внутри метода выполнить метод саплаера get,
 * который возвращает множитель и затем переданное значение на него умножается.
 * Фукнция возращает результат произведения.
 * <p>
 * 4. Создать метод findAllChars. Метод принимает два параметра: String target и char toFind.
 * Первый параметр является входной строкой, а второй - символ, который необходимо найти в входящей строке.
 * Учесть что искомый символ может повторяется (напр.: 'ccch').
 * Необходимо найти все повторения и вернуть в виде конкатенированной строки обвернутый в Optional.
 * Если ни одного совпадения не найдено, тогда необходимо вернуть пустой Optional.
 * Пример выполнения: Optional<String> opt = findAllChars("ccch", 'c'); opt.get(); // вернет "ссс".
 * <p>
 * 5. Создать окно для клиентской части чата: большое текстовое поле для отображения переписки в центре окна.
 * Однострочное текстовое поле для ввода сообщений и кнопка для отсылки сообщений на нижней панели.
 * Сообщение должно отсылаться либо по нажатию кнопки на форме, либо по нажатию кнопки Enter.
 * При «отсылке» сообщение перекидывается из нижнего поля в центральное. (ОПЦИОНАЛЬНО)
 */

public class Main {
    public static void main(String[] args) {
        doTask01();
        doTask02();
        doTask03();
        doTask04();
    }

    public static void doTask01() {
        System.out.println("TASK_01");
        List<String> strings = List.of("1", "12", "123", "1234");
        strings.forEach(System.out::println);
    }

    public static void doTask02() {
        System.out.println("TASK_02");
        Set<String> setStrings = Set.of("1", "12", "123");
        Consumer<String> consumer = System.out::println;
        forItem(setStrings, consumer);
    }

    public static void doTask03() {
        System.out.println("TASK_03");
        int integer = 12;
        Supplier<Integer> supplier = () -> 2;
        System.out.println(doubleUp(integer, supplier));
    }

    public static void doTask04() {
        System.out.println("TASK_04");
        Optional<String> opt = findAllChars("ccch", 'c');
        opt.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("no entries")
        );
    }

    public static void forItem(Set<String> setStrings, Consumer<String> consumer) {
        setStrings.forEach(consumer);
    }

    public static int doubleUp(int integer, Supplier<Integer> supplier) {
        return integer * supplier.get();
    }

    public static Optional<String> findAllChars(String target, char toFind) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == toFind) result.append(toFind);
        }

        if (result.length() == 0)
            return Optional.empty();
        else
            return Optional.of(result.toString());
    }

}
