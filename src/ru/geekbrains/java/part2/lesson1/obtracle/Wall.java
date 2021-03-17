package ru.geekbrains.java.part2.lesson1.obtracle;

import ru.geekbrains.java.part2.lesson1.participant.Jumpable;
import ru.geekbrains.java.part2.lesson1.participant.Participant;

public class Wall implements Obstacle {
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean doIt(Participant participant) {
        if (participant instanceof Jumpable) {
            return (((Jumpable) participant).jump(height));
        }
        return false;
    }
}
