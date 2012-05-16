package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.dynamical.DynamicalCharacter;

abstract public class FieldObject {
    protected Point position;
    final protected FieldManipulator field;
    private char symbol;

    protected FieldObject(FieldManipulator field, Point position, char symbol) {
        if (field == null || position == null) {
            throw new NonPlacedObjectException("Trying to create object" +
                    "without field or coordinates");
        }
        this.field = field;
        this.position = position;
        this.symbol = symbol;
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

    public abstract Point applyEffectTo(DynamicalCharacter object);
}
