package ru.spbau.korovin.se.drunkman.dynamical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.statical.Bottle;
import ru.spbau.korovin.se.drunkman.statical.LyingDrunkMan;
        
/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:40
 */
public interface DynamicObject {
    public void act();

    public Point meetDrunkMan(DrunkMan target);
    public Point meetLyingDrunkMan(LyingDrunkMan violator);
    public Point meetSleepingDrunkMan();
    public Point meetPillar();
    public Point meetBottle(Bottle target);
    public Point meetLamp();
    public Point meetPoliceMan();

    public boolean isValid();
}
