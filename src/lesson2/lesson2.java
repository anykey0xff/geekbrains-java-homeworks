package lesson2;

/**
 * 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
 * С помощью цикла и условия заменить 0 на 1, 1 на 0;
 * <p>
 * 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
 * <p>
 * 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
 * <p>
 * 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое)
 * и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
 * <p>
 * 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
 * <p>
 * 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
 * если в массиве есть место, в котором сумма левой и правой части массива равны.
 * Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
 * граница показана символами ||, эти символы в массив не входят.
 * <p>
 * 7. **** Написать метод, которому на вход подается одномерный массив
 * и число n (может быть положительным, или отрицательным),
 * при этом метод должен сместить все элементы массива на n позиций.
 * Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
 * Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
 * [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
 * При каком n в какую сторону сдвиг можете выбирать сами.
 * <p>
 * Если выполнение задач вызывает трудности, можете обратиться к последней странице методического пособия.
 * Для задач со * не нужно искать решение в интернете, иначе вы теряете весь смысл их выполнения.
 */

public class lesson2 {

    public static void main(String[] args) {
        doTask01();
        doTask02();
        doTask03();
        doTask04();
        doTask05();
        doTask06();
        doTask07();
    }

    private static void printArray(String title, int[] values, int border) {
        System.out.print(title);
        for (int i = 0; i < values.length; i++) {
            if (border != 0 && border == i) {
                System.out.print("|| ");
            }
            System.out.format("%2d ", values[i]);
        }
        System.out.println();
    }

    private static void printMultidimensionalArray(String title, int[][] values) {
        System.out.println(title);
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                System.out.format("%2d ", values[i][j]);
            }
            System.out.println();
        }
    }

    private static void doTask01() {

        System.out.println("Задание 1");

        int[] values = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        printArray("Начальный массив: ", values, 0);

        for (int i = 0; i < values.length; i++) {
            values[i] = (values[i] += 1) % 2;
        }
        printArray("Конечный массив:  ", values, 0);

        System.out.println();

    }

    private static void doTask02() {

        System.out.println("Задание 2");

        int[] values = new int[8];
        printArray("Начальный массив: ", values, 0);

        for (int i = 0; i < values.length; i++) {
            values[i] = i * 3;
        }
        printArray("Конечный массив:  ", values, 0);

        System.out.println();

    }

    private static void doTask03() {

        System.out.println("Задание 3");

        int[] values = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray("Начальный массив: ", values, 0);

        for (int i = 0; i < values.length; i++) {
            if (values[i] < 6) {
                values[i] = values[i] * 2;
            }
        }
        printArray("Конечный массив:  ", values, 0);

        System.out.println();

    }

    private static void doTask04() {

        System.out.println("Задание 4");

        int arraySize = 3;
        int[][] values = new int[arraySize][arraySize];
        printMultidimensionalArray("Начальный массив: ", values);

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                if (i == j || i == (values.length - j - 1)) {
                    values[i][j] = 1;
                }
            }
        }
        printMultidimensionalArray("Конечный массив: ", values);

        System.out.println();
    }

    private static void doTask05() {

        System.out.println("Задание 5");

        int[] values = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray("Начальный массив: ", values, 0);

        int max = values[0];
        int min = values[0];

        for (int i = 0; i < values.length; i++) {
            if (max < values[i]) max = values[i];
            if (min > values[i]) min = values[i];
        }
        System.out.println("max = " + max + ", min = " + min);

        System.out.println();

    }

    private static void doTask06() {

        System.out.println("Задание 6");

        int[] values = {1, 1, 1, 2, 5};
        printArray("Начальный массив: ", values, 0);

        boolean isArrayBalanced = checkBalance(values);

        if (isArrayBalanced) System.out.println("Массив сбалансирован");
        else System.out.println("Массив не сбалансирован");

        System.out.println();

    }

    private static boolean checkBalance(int[] values) {

        if (values.length < 2) return false;

        for (int i = 0; i < values.length - 1; i++) {

            int sumLeft = 0;
            int sumRight = 0;

            for (int j = 0; j < i + 1; j++) {
                sumLeft = sumLeft + values[j];
            }
            for (int j = i + 1; j < values.length; j++) {
                sumRight = sumRight + values[j];
            }
            if (sumLeft == sumRight) {
                printArray("Граница: ", values, i + 1);
                return true;
            }
        }

        return false;

    }

    private static void doTask07() {

        System.out.println("Задание 6");

        int n = -33;
        int[] values = {1, 2, 3};
        System.out.println("Значение сдвига: " + n);
        printArray("Начальный массив: ", values, 0);

        shiftArray(values, n);
        printArray("Конечный массив:  ", values, 0);

        System.out.println();

    }

    public static void shiftArray(int[] values, int n) {

        n = n % values.length;
        if (n == 0)
            return;

        int iterations = 1;
        if ((values.length % n == 0) && (n != 1 && n != -1))
            iterations = (values.length / n - 1);
        if (iterations < 0)
            iterations = -iterations;

        for (int i = 0; i < iterations; i++) {

            int currIndex = i;
            int currBuffer = values[i];
            int nextIndex;
            int nextBuffer;

            do {

                nextIndex = currIndex + n;
                if (nextIndex < 0)
                    nextIndex = nextIndex + values.length;
                if (nextIndex > values.length - 1)
                    nextIndex = nextIndex - values.length;


                nextBuffer = values[nextIndex];
                values[nextIndex] = currBuffer;
                currBuffer = nextBuffer;
                currIndex = nextIndex;

            } while (currIndex != i);

        }
    }

}
