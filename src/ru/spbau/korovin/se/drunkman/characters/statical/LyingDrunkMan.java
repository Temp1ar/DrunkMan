package ru.spbau.korovin.se.drunkman.characters.statical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.dynamical.DynamicObject;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 25.02.12
 * Time: 14:15
 */
public class LyingDrunkMan extends FieldObject {

    public LyingDrunkMan(FieldManipulator field, Point position) {
        super(field, position, '&');
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetLyingDrunkMan(this);
    }

}