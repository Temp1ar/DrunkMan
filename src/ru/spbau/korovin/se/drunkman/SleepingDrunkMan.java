package ru.spbau.korovin.se.drunkman;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 25.02.12
 * Time: 14:44
 */
public class SleepingDrunkMan extends FieldObject {

    public SleepingDrunkMan(Field field, Point position) {
        super(field, position);
        this.symbol = '1';
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetSleepingDrunkMan(this);
    }
}
