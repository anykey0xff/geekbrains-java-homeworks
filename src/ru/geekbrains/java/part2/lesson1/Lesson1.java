package ru.geekbrains.java.part2.lesson1;

import ru.geekbrains.java.part2.lesson1.obtracle.Course;
import ru.geekbrains.java.part2.lesson1.obtracle.Obstacle;
import ru.geekbrains.java.part2.lesson1.obtracle.Track;
import ru.geekbrains.java.part2.lesson1.obtracle.Wall;
import ru.geekbrains.java.part2.lesson1.participant.*;

/**
 * 1. Разобраться с имеющимся кодом (из урока)
 * <p>
 * 2. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
 * Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
 * <p>
 * 3. Создайте два класса: беговая дорожка и стена, при прохождении через которые,
 * участники должны выполнять соответствующие действия (бежать или прыгать),
 * результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
 * <p>
 * 4. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
 * <p>
 * 5. У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
 * Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
 * <p>
 * 6. * Добавить класс Team, который будет содержать: название команды, массив из 4-х участников
 * (т.е. в конструкторе можно сразу всех участников указывать),
 * метод для вывода информации о членах команды прошедших дистанцию, метод вывода информации обо всех членах команды.
 * <p>
 * 7. * Добавить класс Course (полоса препятствий), в котором будут находиться: массив препятствий,
 * метод который будет просить команду пройти всю полосу;
 * То есть в итоге должно быть:
 * public static void main(String[] args) {
 * Course c = new Course(...); // Создаем полосу препятствий
 * Team team = new Team(...); // Создаем команду
 * c.doIt(team); // Просим команду пройти полосу
 * team.showResults(); // Показываем результаты
 * }
 */

public class Lesson1 {

    public static void main(String[] args) {

        Participant[] participants = {
                new Human(1, 1000),
                new Robot(2, 5000),
                new Cat(2)
        };

        Obstacle[] obstacles = {new Wall(2), new Track(1000)};

        for (Participant participant : participants) {
            for (Obstacle obstacle : obstacles) {
                System.out.printf(
                        "%s %s result %b %n",
                        participant.getClass().getSimpleName(),
                        obstacle.getClass().getSimpleName(),
                        obstacle.doIt(participant)
                );
            }
        }

        System.out.println();

        Team team = new Team("First Team", participants);
        team.info();

        System.out.println();

        Course course = new Course(obstacles);
        course.doIt(team);
        team.showResults();

    }
}
