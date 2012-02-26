package ru.spbau.korovin.se.drunkman;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:41
 */
public class Pillar extends FieldObject {

    public Pillar(Field field, Point position) {
        super(field, position);
        this.symbol = '#';
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetPillar();
    }

}
