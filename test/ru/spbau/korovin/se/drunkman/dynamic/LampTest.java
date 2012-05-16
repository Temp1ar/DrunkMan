package ru.spbau.korovin.se.drunkman.dynamic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.PoliceDispatcher;
import ru.spbau.korovin.se.drunkman.characters.dynamical.Beggar;
import ru.spbau.korovin.se.drunkman.characters.dynamical.DrunkMan;
import ru.spbau.korovin.se.drunkman.characters.dynamical.Lamp;
import ru.spbau.korovin.se.drunkman.characters.dynamical.PoliceMan;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;
import ru.spbau.korovin.se.drunkman.characters.statical.Pillar;
import ru.spbau.korovin.se.drunkman.field.Field;
import ru.spbau.korovin.se.drunkman.field.RectangularField;
import ru.spbau.korovin.se.drunkman.random.MathRandom;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class LampTest {
    private Field field;
    private Lamp lamp;
    private final int radius = 3;

    @Before
    public void createEnvironment() {
        this.field = spy(new RectangularField(15, 15));
        Point lampPosition = new Point(9, 3);
        this.lamp = new Lamp(field, lampPosition, radius);
        field.placeObject(lamp);
    }

    @After
    public void resetEnvironment() {
        this.field = spy(new RectangularField(15, 15));
        PoliceDispatcher policeDispatcher = PoliceDispatcher.getInstance();
        while (policeDispatcher.queueSize() > 0) {
            policeDispatcher.markVialator();
            policeDispatcher.popVialator();
        }
    }

    @Test
    public void soberDay() {
        lamp.act();
        assertEquals(0, PoliceDispatcher.getInstance().queueSize());
    }

    @Test
    public void drunkersEverywhere() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                DrunkMan mockDrunkMan =
                        spy(new DrunkMan(field, new Point(i, j), new MathRandom()));
                when(mockDrunkMan.getState()).thenReturn(DrunkMan.State.LYING);
                field.placeObject(mockDrunkMan);
            }
        }
        lamp.act();

        PoliceDispatcher policeDispatcher = PoliceDispatcher.getInstance();
        // Checking that all drunkers around are detected
        assertEquals((radius * 2 + 1) * (radius * 2 + 1) - 1,
                policeDispatcher.queueSize());

    }

    @Test
    public void partyingHardUnderLamp() {
        DrunkMan walkingDrunkMan =
                spy(new DrunkMan(field, new Point(6, 0), new MathRandom()));
        when(walkingDrunkMan.getState()).thenReturn(DrunkMan.State.WALKING);
        field.placeObject(walkingDrunkMan);

        DrunkMan sleepingDrunkMan =
                spy(new DrunkMan(field, new Point(7, 0), new MathRandom()));
        when(sleepingDrunkMan.getState()).thenReturn(DrunkMan.State.SLEEPING);
        field.placeObject(sleepingDrunkMan);

        DrunkMan lyingDrunkMan =
                spy(new DrunkMan(field, new Point(8, 0), new MathRandom()));
        when(lyingDrunkMan.getState()).thenReturn(DrunkMan.State.LYING);
        field.placeObject(lyingDrunkMan);

        field.placeObject(new PoliceMan(field, new Point(9, 0), new Point(9, 0)));
        field.placeObject(new Lamp(field, new Point(10, 0), 1));
        field.placeObject(new Bottle(field, new Point(11, 0)));
        field.placeObject(new Pillar(field, new Point(12, 0)));
        field.placeObject(new Beggar(field, new Point(6, 1)));

        lamp.act();
        PoliceDispatcher policeDispatcher = PoliceDispatcher.getInstance();
        assertEquals(1, policeDispatcher.queueSize());
    }
}
