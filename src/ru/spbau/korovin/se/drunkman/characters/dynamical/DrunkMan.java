package ru.spbau.korovin.se.drunkman.characters.dynamical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.random.RandomGenerator;

public class DrunkMan extends DynamicalCharacter {
    private static final double BOTTLE_DROP_CHANCE = 1d / 30;
    final private RandomGenerator random;

    public enum State {
        WALKING,
        SLEEPING,
        LYING
    }

    private State state = State.WALKING;

    public DrunkMan(FieldManipulator field, Point position,
                    RandomGenerator random) {
        super(field, position, '@');
        this.random = random;
    }

    @Override
    public Point applyEffectTo(DynamicalCharacter object) {
        return object.meetDrunkMan(this);
    }

    public void act() {
        if (state != State.WALKING) {
            return;
        }
        Point oldPosition = position;

        super.act();

        // If we move somewhere then we decide to drop the bottle
        if (position != oldPosition) {
            if (random.nextDouble() <= BOTTLE_DROP_CHANCE) {
                field.placeObject(new Bottle(field, oldPosition));
            }
        }
    }

    @Override
    public Point meetDrunkMan(DrunkMan drunkMan) {
        if (drunkMan.getState() == State.SLEEPING) {
            return meetPillar();
        }
        return position;
    }

    @Override
    public Point meetPillar() {
        state = State.SLEEPING;
        return position;
    }

    @Override
    public Point meetBottle(Bottle target) {
        // Breaking bottle
        field.removeObject(target);
        state = State.LYING;
        return target.getPosition();
    }

    @Override
    protected Point nextMove() {
        newDirection();
        return nextDirectedMove();
    }

    public State getState() {
        return state;
    }

    @Override
    public char getSymbol() {
        switch (state) {
            case LYING:
                return '&';
            case SLEEPING:
                return '!';
            default:
                return '@';
        }
    }
}
