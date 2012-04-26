package ru.spbau.korovin.se.drunkman.characters.statical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.dynamical.DynamicObject;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:40
 */
public class Bottle extends FieldObject {

    public Bottle(FieldManipulator field, Point position) {
        super(field, position, 'B');
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetBottle(this);
    }
}