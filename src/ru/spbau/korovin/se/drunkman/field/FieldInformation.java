package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 15.03.12
 * Time: 22:08
 */
public interface FieldInformation {
    int getWidth();

    int getHeight();

    boolean isFree(Point point);
}
