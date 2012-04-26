package ru.spbau.korovin.se.drunkman.characters.dynamical;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;
import ru.spbau.korovin.se.drunkman.characters.statical.LyingDrunkMan;

public interface DynamicObject {
    public void act();

    public Point meetDrunkMan(DrunkMan target);

    public Point meetLyingDrunkMan(LyingDrunkMan violator);

    public Point meetSleepingDrunkMan();

    public Point meetPillar();

    public Point meetBottle(Bottle target);

    public Point meetLamp();

    public Point meetPoliceMan();

    public Point meetBeggar(Beggar beggar);

    public boolean isValid();
}
