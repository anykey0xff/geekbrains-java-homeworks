package ru.geekbrains.java.part2.lesson1.participant;

import ru.geekbrains.java.part2.lesson1.obtracle.Obstacle;

public class Team {

    Participant[] participants;
    String commandName;
    String[] results;

    public Team(String commandName, Participant[] participants) {
        this.commandName = commandName;
        this.participants = participants;

        results = new String[participants.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = participants[i].getClass().getSimpleName() + " have no results";
        }
    }

    public void info() {
        System.out.println("Command name: " + commandName);
        for (int i = 0; i < participants.length; i++) {
            System.out.printf("Participant %d is %s %n", i + 1, participants[i].getClass().getSimpleName());
        }
    }

    public void showResults() {
        System.out.println("Command name: " + commandName);
        for (int i = 0; i < participants.length; i++) {
            System.out.println(results[i]);
        }
    }

    public void passObstacles(Obstacle[] obstacles) {
        for (int i = 0; i < participants.length; i++) {
            results[i] = String.format(
                    "Participant %d (%s) results: ",
                    i + 1,
                    participants[i].getClass().getSimpleName()
            );
            for (Obstacle obstacle : obstacles) {
                String result = String.format(
                        "%s %b ",
                        obstacle.getClass().getSimpleName(),
                        obstacle.doIt(participants[i])
                );
                results[i] = results[i] + result;
            }
        }
    }
}
