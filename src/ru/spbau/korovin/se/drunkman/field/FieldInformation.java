package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;

import java.util.List;

public interface FieldInformation {
    int getWidth();

    int getHeight();

    boolean isFree(Point point);

    List<Point> getDirections();
}
