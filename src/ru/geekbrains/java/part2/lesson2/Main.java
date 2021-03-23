package ru.geekbrains.java.part2.lesson2;

/**
 * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
 * при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 * <p>
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
 * Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
 * должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
 * <p>
 * 3. В методе main() вызвать полученный метод,
 * обработать возможные исключения MySizeArrayException и MyArrayDataException, и вывести результат расчета.
 */

public class Main {

    public static void main(String[] args) {
        String[][] strings = getArray();
        try {
            System.out.println("Sum of numbers is " + sumArray(strings));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static String[][] getArray() {
        return new String[4][3];
    }

    public static int sumArray(String[][] strings) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        int number;
        if (strings.length != 4) throw new MyArraySizeException("Array size must be 4х4");
        for (String[] string : strings) {
            if (string.length != 4) throw new MyArraySizeException("Array size must be 4х4");
        }
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                try {
                    number = Integer.parseInt(strings[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Invalid value in a cell %d:%d %n", i + 1, j + 1), e);
                }
                sum += number;
            }
        }
        return sum;
    }

}
