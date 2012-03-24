package ru.spbau.korovin.se.drunkman.statical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.dynamical.DynamicObject;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:41
 */
public class Pillar extends FieldObject {

    public Pillar(FieldManipulator field, Point position) {
        super(field, position);
        this.symbol = '#';
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetPillar();
    }

}
