package ru.spbau.korovin.se.drunkman.characters.dynamical;

import ru.spbau.korovin.se.drunkman.PathFinder;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;

import java.util.List;

public class Beggar extends DynamicalCharacter {
    private boolean hasBottle = false;
    private final Point home;
    private boolean isSpending = false;
    private final int stepsSpending = 40;
    private int spendingCounter;

    public Beggar(FieldManipulator field, Point home) {
        super(field, home, 'Z');
        this.home = home;
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

        super.act();

        if (hasBottle && position.equals(home)) {
            hasBottle = false;
            field.removeObject(this);
            isSpending = true;
            spendingCounter = stepsSpending;
        }
    }

    @Override
    protected Point nextMove() {
        Point nextMove;
        if (hasBottle) {
            PathFinder pf = new PathFinder(field, home);
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

    @Override
    public Point meetBottle(Bottle target) {
        hasBottle = true;
        return target.getPosition();
    }

    @Override
    public Point applyEffectTo(DynamicalCharacter object) {
        return object.meetBeggar();
    }
}
