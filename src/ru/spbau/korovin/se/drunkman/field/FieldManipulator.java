package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;

import java.util.List;

public interface FieldManipulator extends FieldInformation {
    boolean isAvailable(Point p);

    FieldObject getObject(Point p);

    boolean placeObject(FieldObject object);

    void removeObject(FieldObject object);

    void changePosition(Point position, Point to);

    List<Point> getAvailableNeighbours(Point position);
}
