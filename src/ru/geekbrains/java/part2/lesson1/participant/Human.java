package ru.geekbrains.java.part2.lesson1.participant;

public class Human implements Jumpable, Runnable, Participant {

    private final int maxJumpDistance;
    private final int maxRunDistance;

    public Human(int maxJumpDistance, int maxRunDistance) {
        this.maxJumpDistance = maxJumpDistance;
        this.maxRunDistance = maxRunDistance;
    }

    @Override
    public boolean jump(int distance) {
        return maxJumpDistance >= distance;
    }

    @Override
    public boolean run(int distance) {
        return maxRunDistance >= distance;
    }

}
