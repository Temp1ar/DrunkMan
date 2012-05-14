package ru.spbau.korovin.se.drunkman.characters.dynamical;

import ru.spbau.korovin.se.drunkman.PathFinder;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.PoliceDispatcher;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;
import ru.spbau.korovin.se.drunkman.characters.statical.LyingDrunkMan;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.FieldObject;

import java.util.List;

public class PoliceMan extends FieldObject implements DynamicObject {
    private boolean isValid = true;
    private Point target = null;
    final private Point station;

    public PoliceMan(FieldManipulator field,
                     Point station, Point target) {
        super(field, station, '!');
        this.target = target;
        this.station = station;
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetPoliceMan();
    }


    @Override
    public void act() {
        PathFinder pf = new PathFinder(field, target);
        List<Point> path = pf.compute(position);
        if (path != null) {
            Point nextMove = path.get(1);
            FieldObject targetObject = field.getObject(nextMove);
            if (targetObject != null) {
                nextMove = targetObject.applyEffectTo(this);
            }

            changePosition(nextMove);

            if (target == station && position.equals(station)) {
                PoliceDispatcher.getInstance().popVialator();
                destroy();
            }
        }
    }

    private void destroy() {
        field.removeObject(this);
        isValid = false;
    }

    @Override
    public Point meetDrunkMan(DrunkMan target) {
        return position;
    }

    @Override
    public Point meetLyingDrunkMan(LyingDrunkMan violator) {
        if (violator.getPosition() != target) {
            return position;
        }

        field.removeObject(violator);
        target = station;

        return violator.getPosition();
    }

    @Override
    public Point meetSleepingDrunkMan() {
        return position;
    }

    @Override
    public Point meetPillar() {
        return position;
    }

    @Override
    public Point meetBottle(Bottle target) {
        return position;
    }

    @Override
    public Point meetLamp() {
        return position;
    }

    @Override
    public Point meetPoliceMan() {
        return position;
    }

    @Override
    public Point meetBeggar(Beggar beggar) {
        return position;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }
}
