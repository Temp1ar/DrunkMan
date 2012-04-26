package ru.spbau.korovin.se.drunkman.characters.dynamical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.PoliceDispatcher;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;
import ru.spbau.korovin.se.drunkman.characters.statical.LyingDrunkMan;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:41
 */
public class Lamp extends FieldObject implements DynamicObject {
    final private int radius;

    public Lamp(FieldManipulator field, Point position, int radius) {
        super(field, position, 'Ф');
        this.radius = radius;
    }

    @Override
    public void act() {
        Point c = getPosition();
        for (int y = c.y - radius; y < c.y + radius + 1; y++) {
            for (int x = c.x - radius; x < c.x + radius + 1; x++) {
                Point p = new Point(x, y);
                FieldObject fO = field.getObject(p);
                if (fO != null && fO instanceof LyingDrunkMan) {
                    PoliceDispatcher.getInstance().addVialator(fO);
                }
            }
        }
    }

    @Override
    public Point meetDrunkMan(DrunkMan target) {
        return null;
    }

    @Override
    public Point meetLyingDrunkMan(LyingDrunkMan target) {
        return null;
    }

    @Override
    public Point meetSleepingDrunkMan() {
        return null;
    }

    @Override
    public Point meetPillar() {
        return null;
    }

    @Override
    public Point meetBottle(Bottle target) {
        return null;
    }

    @Override
    public Point meetLamp() {
        return null;
    }

    @Override
    public Point meetPoliceMan() {
        return null;
    }

    @Override
    public Point meetBeggar(Beggar beggar) {
        return null;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetLamp();
    }
}
