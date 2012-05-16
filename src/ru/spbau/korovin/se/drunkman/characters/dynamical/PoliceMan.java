package ru.spbau.korovin.se.drunkman.characters.dynamical;

import ru.spbau.korovin.se.drunkman.PathFinder;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.PoliceDispatcher;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;

import java.util.List;

public class PoliceMan extends DynamicalCharacter {
    private Point target = null;
    final private Point home;

    public PoliceMan(FieldManipulator field,
                     Point home, Point target) {
        super(field, home, '!');
        this.target = target;
        this.home = home;
    }

    @Override
    public Point applyEffectTo(DynamicalCharacter object) {
        return object.meetPoliceMan();
    }

    @Override
    public void act() {
        super.act();

        if (target == home && position.equals(home)) {
            PoliceDispatcher.getInstance().popVialator();
            destroy();
        }
    }

    @Override
    protected Point nextMove() {
        PathFinder pf = new PathFinder(field, target);
        List<Point> path = pf.compute(position);
        if (path != null) {
            return path.get(1);
        } else {
            return position;
        }
    }

    private void destroy() {
        field.removeObject(this);
        setValid(false);
    }

    @Override
    public Point meetDrunkMan(DrunkMan violator) {
        if (violator.getState() != DrunkMan.State.LYING) {
            return position;
        }

        if (violator.getPosition() != target) {
            return position;
        }

        field.removeObject(violator);
        violator.setValid(false);
        target = home;

        return violator.getPosition();
    }

}
