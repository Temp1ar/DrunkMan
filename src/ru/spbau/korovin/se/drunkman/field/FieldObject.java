package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.dynamical.DynamicObject;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:40
 */
abstract public class FieldObject {
    protected Point position;
    final protected FieldManipulator field;
    protected char symbol;

    protected FieldObject(FieldManipulator field, Point position) {
        if(field == null || position == null) {
            throw new NonPlacedObjectException("Trying to create object" +
                    "without field or coordinates");
        }
        this.field = field;
        this.position = position;
    }

    public char getSymbol() {
        return symbol;
    }

    public Point getPosition() {
        return position;
    }

    protected void changePosition(Point to) {
        field.changePosition(position, to);
        position = to;
    }

    public abstract Point applyEffectTo(DynamicObject object);
}
