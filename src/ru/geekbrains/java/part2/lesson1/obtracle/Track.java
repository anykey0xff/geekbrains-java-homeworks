package ru.geekbrains.java.part2.lesson1.obtracle;

import ru.geekbrains.java.part2.lesson1.participant.Participant;
import ru.geekbrains.java.part2.lesson1.participant.Runnable;

public class Track implements Obstacle {
    private final int width;

    public Track(int width) {
        this.width = width;
    }

    @Override
    public boolean doIt(Participant participant) {
        if (participant instanceof Runnable) {
            return (((Runnable) participant).run(width));
        }
        return false;
    }
}
