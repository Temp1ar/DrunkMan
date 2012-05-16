package ru.spbau.korovin.se.drunkman.characters.statical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.dynamical.DynamicalCharacter;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

public class Bottle extends FieldObject {

    public Bottle(FieldManipulator field, Point position) {
        super(field, position, 'B');
    }

    @Override
    public Point applyEffectTo(DynamicalCharacter object) {
        return object.meetBottle(this);
    }
}
