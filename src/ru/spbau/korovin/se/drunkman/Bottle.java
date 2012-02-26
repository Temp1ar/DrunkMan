package ru.spbau.korovin.se.drunkman;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:40
 */
public class Bottle extends FieldObject {

    public Bottle(Field field, Point position) {
        super(field, position);
        this.symbol = 'B';
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetBottle(this);
    }
}
