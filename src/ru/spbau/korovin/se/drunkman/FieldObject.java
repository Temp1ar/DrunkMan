package ru.spbau.korovin.se.drunkman;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:40
 */
abstract public class FieldObject {
    protected Point position;
    final protected Field field;
    protected char symbol;

    public FieldObject(Field field, Point position) {
        this.field = field;
        this.position = position;
    }

    public char getSymbol() {
        return symbol;
    }

    public Point getPosition() {
        return position;
    }

    protected Point getRandomNeighbor() {
        Random number = new Random();
        boolean up = number.nextBoolean();
        boolean left = number.nextBoolean();
        boolean horizontal = number.nextBoolean();

        int x, y;
        if (horizontal) {
            x = left ? position.x - 1 : position.x + 1;
            y = position.y;
        } else {
            x = position.x;
            y = up ? position.y - 1 : position.y + 1;
        }

        return new Point(x, y);
    }


    public Point nextMove() {
        Point newPosition = getRandomNeighbor();
        int i = 4;
        while(!field.isAvailable(newPosition) && (i > 0)) {
            newPosition = getRandomNeighbor();
            i--;
        }
        
        if(i == 0) {
            newPosition = position;
        }

        return newPosition;
    }

    public void changePosition(Point to) {
        field.changePosition(position, to);
        position = to;
    }

    abstract protected Point applyEffectTo(DynamicObject object);
}
