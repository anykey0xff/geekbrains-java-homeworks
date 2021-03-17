package ru.geekbrains.java.part2.lesson1.participant;

public class Cat implements Jumpable, Participant {

    private final int maxJumpDistance;

    public Cat(int maxJumpDistance) {
        this.maxJumpDistance = maxJumpDistance;
    }

    @Override
    public boolean jump(int distance) {
        return maxJumpDistance >= distance;
    }

}
