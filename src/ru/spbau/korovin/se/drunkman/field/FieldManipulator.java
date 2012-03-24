package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 15.03.12
 * Time: 23:32
 */
public interface FieldManipulator {
    boolean isAvailable(Point p);

    FieldObject getObject(Point p);

    boolean placeObject(FieldObject object);

    void removeObject(FieldObject object);

    void changePosition(Point position, Point to);
}
