package ru.spbau.korovin.se.drunkman.characters.statical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.dynamical.DynamicObject;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

public class SleepingDrunkMan extends FieldObject {

    public SleepingDrunkMan(FieldManipulator field, Point position) {
        super(field, position, '1');
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetSleepingDrunkMan();
    }
}
