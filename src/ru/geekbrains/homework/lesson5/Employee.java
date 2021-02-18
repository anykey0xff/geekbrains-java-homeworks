package ru.geekbrains.homework.lesson5;

public class Employee {

    private String fullName;
    private String position;
    private String emailAddress;
    private String phoneNumber;
    private int salary;
    private int age;

    public Employee(String fullName, String position, String email, String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.emailAddress = email;
        this.phoneNumber = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    private String getFullInformation() {
        String fullInformationTemplate = """
                Full name: %s
                Position: %s
                email: %s
                phone: %s
                salary: %d
                age: %d
                """;
        return String.format(fullInformationTemplate, fullName, position, emailAddress, phoneNumber, salary, age);
    }

    public void printFullInformation() {
        System.out.println(getFullInformation());
    }
}
