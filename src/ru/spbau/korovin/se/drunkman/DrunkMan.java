package ru.spbau.korovin.se.drunkman;


import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:40
 */
public class DrunkMan extends FieldObject implements DynamicObject {
    private static final double BOTTLE_DROP_CHANCE = 1d/30;
    private boolean isValid = true;

    public DrunkMan(Field field, Point position) {
        super(field, position);
        this.symbol = '@';
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetDrunkMan(this);
    }

    public void move() {
        Point nextMove = nextMove();
        FieldObject targetObject = field.getObject(nextMove);
        if(targetObject != null) {
            nextMove = targetObject.applyEffectTo(this);
        }

        Point oldPosition = position;

        // If we move somewhere then we decide to drop the bottle
        if (nextMove != oldPosition) {
            if(isValid()) {
                changePosition(nextMove);
            }
            Random number = new Random();
            if(number.nextFloat() <= BOTTLE_DROP_CHANCE) {
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
    public Point meetSleepingDrunkMan(SleepingDrunkMan target) {
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
    public Point meetLamp(Lamp lamp) {
        //return meetPillar();
        return position;
    }

    @Override
    public Point meetPoliceMan(PoliceMan policeMan) {
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

}
