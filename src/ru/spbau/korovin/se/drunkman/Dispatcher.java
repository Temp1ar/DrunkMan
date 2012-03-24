package ru.spbau.korovin.se.drunkman;

import ru.spbau.korovin.se.drunkman.field.FieldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 22.02.12
 * Time: 17:50
 */
public class Dispatcher {
    private final List<FieldObject> vialators = new ArrayList<>();
    private int pointer = 0;
    private static final Dispatcher ourInstance = new Dispatcher();

    public static Dispatcher getInstance() {
        return ourInstance;
    }

    public void addVialator(FieldObject object) {
        if(!vialators.contains(object)) {
            vialators.add(object);
        }
    }

    public Point getVialatorPosition() {
        try {
            return vialators.get(pointer).getPosition();
        } catch(NoSuchElementException e) {
            return null;
        }
    }

    public void markVialator() {
        pointer++;
    }

    public int queueSize() {
        return vialators.size() - pointer;
    }

    public void popVialator() {
        vialators.remove(0);
        pointer--;
    }

    private Dispatcher() {
    }
}
