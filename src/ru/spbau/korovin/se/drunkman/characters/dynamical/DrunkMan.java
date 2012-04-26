package ru.spbau.korovin.se.drunkman.characters.dynamical;


import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;
import ru.spbau.korovin.se.drunkman.characters.statical.LyingDrunkMan;
import ru.spbau.korovin.se.drunkman.characters.statical.SleepingDrunkMan;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;
import ru.spbau.korovin.se.drunkman.random.RandomGenerator;

import java.util.List;
import java.util.Random;

public class DrunkMan extends FieldObject implements DynamicObject {
    private static final double BOTTLE_DROP_CHANCE = 1d / 30;
    private boolean isValid = true;
    final private RandomGenerator random;

    public DrunkMan(FieldManipulator field, Point position,
                    RandomGenerator random) {
        super(field, position, '@');
        this.random = random;
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetDrunkMan(this);
    }

    public void act() {
        Point nextMove = nextMove();
        FieldObject targetObject = field.getObject(nextMove);
        if (targetObject != null) {
            nextMove = targetObject.applyEffectTo(this);
        }

        Point oldPosition = position;

        // If we move somewhere then we decide to drop the bottle
        if (nextMove != oldPosition) {
            if (isValid()) {
                changePosition(nextMove);
            }

            if (random.nextDouble()
                    <= BOTTLE_DROP_CHANCE) {
                field.placeObject(new Bottle(field, oldPosition));
            }
        }
    }


    @Override
    public Point meetDrunkMan(DrunkMan target) {
        return position;
    }

    @Override
    public Point meetLyingDrunkMan(LyingDrunkMan target) {
        return position;
    }

    @Override
    public Point meetSleepingDrunkMan() {
        return meetPillar();
    }

    @Override
    public Point meetPillar() {
        destroy();
        field.placeObject(new SleepingDrunkMan(field, position));
        return position;
    }

    private void destroy() {
        field.removeObject(this);
        this.isValid = false;
    }

    @Override
    public Point meetLamp() {
        //return meetPillar();
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
        return isValid;
    }

    @Override
    public Point meetBottle(Bottle target) {
        // Breaking bottle
        field.removeObject(target);

        // Removing drunk man
        destroy();

        // Placing lying drunk man
        field.placeObject(new LyingDrunkMan(field, target.getPosition()));

        return target.getPosition();
    }

    private Point nextMove() {
        List<Point> possibleMoves = field.getAvailableNeighbours(position);

        int size = possibleMoves.size();
        if (size > 0) {
            Random random = new Random();
            return possibleMoves.get(random.nextInt(size));
        } else {
            return position;
        }

    }
}
