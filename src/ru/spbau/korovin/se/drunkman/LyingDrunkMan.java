package ru.spbau.korovin.se.drunkman;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 25.02.12
 * Time: 14:15
 */
public class LyingDrunkMan extends FieldObject {

    public LyingDrunkMan(Field field, Point position) {
        super(field, position);
        this.symbol = '&';
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetLyingDrunkMan(this);
    }

}
