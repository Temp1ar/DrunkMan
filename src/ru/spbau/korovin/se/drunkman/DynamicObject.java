package ru.spbau.korovin.se.drunkman;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:40
 */
public interface DynamicObject {
    public void move();

    public Point meetDrunkMan(DrunkMan target);
    public Point meetLyingDrunkMan(LyingDrunkMan violator);
    public Point meetSleepingDrunkMan(SleepingDrunkMan target);
    public Point meetPillar();
    public Point meetBottle(Bottle target);
    public Point meetLamp(Lamp lamp);
    public Point meetPoliceMan(PoliceMan policeMan);

    public boolean isValid();
}
