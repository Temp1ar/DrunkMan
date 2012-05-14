package ru.spbau.korovin.se.drunkman.characters.statical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.dynamical.DynamicObject;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

public class Pillar extends FieldObject {

    public Pillar(FieldManipulator field, Point position) {
        super(field, position, '#');
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetPillar();
    }

}
