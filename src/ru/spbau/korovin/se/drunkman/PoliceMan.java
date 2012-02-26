package ru.spbau.korovin.se.drunkman;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:41
 */
public class PoliceMan extends FieldObject implements DynamicObject {
    private boolean isValid = true;
    private Point target = null;
    final private Point station;
    
    public PoliceMan(Field field, Point station, Point target) {
        super(field, station);
        this.symbol = '!';
        this.target = target;
        this.station = station;
    }

    @Override
    public Point applyEffectTo(DynamicObject object) {
        return object.meetPoliceMan(this);
    }


    @Override
    public void move() {
        PathFinder pf = new PathFinder(field, target);
        List<Point> path = pf.compute(position);
        if(path != null) {
            Point nextMove = path.get(1);
            FieldObject targetObject = field.getObject(nextMove);
            if(targetObject != null) {
                nextMove = targetObject.applyEffectTo(this);
            }

            changePosition(nextMove);

            if(target == station && position.equals(station)) {
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
        if(violator.getPosition() != target) {
            return position;
        }
        
        field.removeObject(violator);
        Dispatcher.getInstance().popVialator();
        target = station;
        
        return violator.getPosition();
    }

    @Override
    public Point meetSleepingDrunkMan(SleepingDrunkMan target) {
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
    public Point meetLamp(Lamp lamp) {
        return position;
    }

    @Override
    public Point meetPoliceMan(PoliceMan policeMan) {
        return position;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }
}
