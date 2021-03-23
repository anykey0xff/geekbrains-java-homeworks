package ru.geekbrains.java.part1.lesson5;

/**
 * 1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
 * 2. Конструктор класса должен заполнять эти поля при создании объекта.
 * 3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
 * 4. Создать массив из 5 сотрудников.
 * Пример:
 * Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
 * persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30); // потом для каждой ячейки массива задаем объект
 * persArray[1] = new Person(...);
 * ...
 * persArray[4] = new Person(...);
 *
 * 5. С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
 */

public class Main {

    public static void main(String[] args) {
        doTask04();
        doTask05();
    }

    private static void doTask05() {
        Employee[] employees = createEmployees();
        outputEmployeesOlderThen40(employees);
    }

    private static void outputEmployeesOlderThen40(Employee[] employees) {
        for (Employee employee : employees) {
            if (employee.getAge() >= 40)
                employee.printFullInformation();
        }
    }


    private static void doTask04() {
        Employee[] employees = createEmployees();
    }

    private static Employee[] createEmployees() {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        employees[1] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 35000, 35);
        employees[2] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 40000, 40);
        employees[3] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 45000, 45);
        employees[4] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 50000, 50);
        return employees;
    }

}
