package ru.spbau.korovin.se.drunkman.characters.dynamical;

import ru.spbau.korovin.se.drunkman.PathFinder;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;
import ru.spbau.korovin.se.drunkman.characters.statical.LyingDrunkMan;
import ru.spbau.korovin.se.drunkman.field.FieldInformation;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Beggar extends FieldObject implements DynamicObject {
    private boolean hasBottle = false;
    private final FieldInformation fieldInformation;
    private final Point home;
    private boolean isSpending = false;
    private Point direction;
    private final int stepsSpending = 40;
    private int spendingCounter;

    public Beggar(FieldManipulator fieldManipulator,
                  FieldInformation fieldInformation, Point position) {
        super(fieldManipulator, position, 'Z');
        this.fieldInformation = fieldInformation;
        home = position;
        chooseDirection(fieldInformation.getDirections());
    }

    @Override
    public void act() {
        if (isSpending) {
            spendingCounter--;
            if (spendingCounter > 0) {
                return;
            }
            field.placeObject(this);
            isSpending = false;
        }

        Point nextMove = nextMove();

        FieldObject targetObject = field.getObject(nextMove);
        if (targetObject != null) {
            nextMove = targetObject.applyEffectTo(this);
        }

        if (position.equals(nextMove)) {
            chooseDirection(fieldInformation.getDirections());
        }

        changePosition(nextMove);

        if (hasBottle && position.equals(home)) {
            hasBottle = false;
            field.removeObject(this);
            isSpending = true;
            spendingCounter = stepsSpending;
        }
    }

    private Point nextMove() {
        Point nextMove;
        if (hasBottle) {
            PathFinder pf = new PathFinder(fieldInformation, home);
            List<Point> path = pf.compute(position);
            if (path == null) {
                nextMove = position;
            } else {
                nextMove = path.get(1);
            }
        } else {
            nextMove = nextDirectedMove();
        }
        return nextMove;
    }

    private void chooseDirection(List<Point> possibleDirections) {
        Random random = new Random();
        int size = possibleDirections.size();
        direction = possibleDirections.get(random.nextInt(size));
    }

    private Point nextDirectedMove() {
        ArrayList<Point> possibleDirections =
                new ArrayList<>(fieldInformation.getDirections());

        Point nextMove = position.plus(direction);
        List<Point> possibleMoves = field.getAvailableNeighbours(position);
        while (!possibleMoves.contains(nextMove)
                && !possibleDirections.isEmpty()) {
            possibleDirections.remove(direction);
            chooseDirection(possibleDirections);
            nextMove = position.plus(direction);
        }

        return possibleDirections.isEmpty() ? position : nextMove;
    }

    @Override
    public Point meetDrunkMan(DrunkMan target) {
        return position;
    }

    @Override
    public Point meetLyingDrunkMan(LyingDrunkMan violator) {
        return position;
    }

    @Override
    public Point meetSleepingDrunkMan() {
        return position;
    }

    @Override
    public Point meetPillar() {
        return position;
    }

    @Override
    public Point meetBottle(Bottle target) {
        hasBottle = true;
        return target.getPosition();
    }

    @Override
    public Point meetLamp() {
        return position;
    }

    @Override
    public Point meetPoliceMan() {
        return position;
    }

    @Override
    public Point meetBeggar(Beggar beggar) {
        return position;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetBeggar(this);
    }
}
