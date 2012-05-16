package ru.spbau.korovin.se.drunkman.characters.dynamical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.PoliceDispatcher;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

public class Lamp extends DynamicalCharacter {
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
                if (fO != null) {
                    fO.applyEffectTo(this);
                }
            }
        }
    }

    @Override
    protected Point nextMove() {
        return position;
    }

    @Override
    public Point meetDrunkMan(DrunkMan target) {
        if (target.getState() == DrunkMan.State.LYING) {
            PoliceDispatcher.getInstance().addVialator(target);
        }
        return null;
    }

    @Override
    public Point applyEffectTo(DynamicalCharacter object) {
        return object.meetLamp();
    }
}
