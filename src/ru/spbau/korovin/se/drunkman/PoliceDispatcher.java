package ru.spbau.korovin.se.drunkman;

import ru.spbau.korovin.se.drunkman.field.FieldObject;

import java.util.ArrayList;
import java.util.List;

public class PoliceDispatcher {
    private final List<FieldObject> vialators = new ArrayList<>();
    private int pointer = 0;
    private static final PoliceDispatcher ourInstance = new PoliceDispatcher();

    public static PoliceDispatcher getInstance() {
        return ourInstance;
    }

    public void addVialator(FieldObject object) {
        if (!vialators.contains(object)) {
            vialators.add(object);
        }
    }

    public Point getVialatorPosition() {
        return vialators.get(pointer).getPosition();
    }

    public void markVialator() {
        pointer++;
    }

    public int queueSize() {
        return vialators.size() - pointer;
    }

    public int markedSize() {
        return pointer;
    }

    public void popVialator() {
        vialators.remove(0);
        pointer--;
    }

    private PoliceDispatcher() {
    }
}
