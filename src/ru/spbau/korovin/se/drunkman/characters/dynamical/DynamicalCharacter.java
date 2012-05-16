package ru.spbau.korovin.se.drunkman.characters.dynamical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class DynamicalCharacter extends FieldObject {
    private Point direction = new Point(0, 0);
    private boolean isValid = true;

    protected DynamicalCharacter(FieldManipulator field, Point position, char symbol) {
        super(field, position, symbol);
        newDirection();
    }

    public void act() {
        Point nextMove = nextMove();

        FieldObject targetObject = field.getObject(nextMove);
        if (targetObject != null) {
            nextMove = targetObject.applyEffectTo(this);
        }

        if (position.equals(nextMove)) {
            newDirection();
        }

        changePosition(nextMove);
    }

    protected abstract Point nextMove();

    protected void newDirection() {
        List<Point> possibleDirections = field.getDirections();
        Random random = new Random();
        int size = possibleDirections.size();
        direction = possibleDirections.get(random.nextInt(size));
    }

    protected Point nextDirectedMove() {
        ArrayList<Point> possibleDirections =
                new ArrayList<>(field.getDirections());

        Point nextMove = position.plus(direction);
        List<Point> possibleMoves = field.getAvailableNeighbours(position);
        while (!possibleMoves.contains(nextMove)
                && !possibleDirections.isEmpty()) {
            possibleDirections.remove(direction);
            newDirection();
            nextMove = position.plus(direction);
        }

        return possibleDirections.isEmpty() ? position : nextMove;
    }

    public Point meetDrunkMan(DrunkMan target) {
        return position;
    }

    public Point meetPillar() {
        return position;
    }

    public Point meetBottle(Bottle target) {
        return position;
    }

    public Point meetLamp() {
        return position;
    }

    public Point meetPoliceMan() {
        return position;
    }

    public Point meetBeggar() {
        return position;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}
