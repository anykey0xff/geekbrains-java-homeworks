package ru.geekbrains.java.part2.lesson1.obtracle;

import ru.geekbrains.java.part2.lesson1.participant.Team;

public class Course {

    Obstacle[] obstacles;

    public Course(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        team.passObstacles(obstacles);
    }
}
