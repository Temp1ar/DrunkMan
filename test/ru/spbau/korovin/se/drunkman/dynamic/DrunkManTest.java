package ru.spbau.korovin.se.drunkman.dynamic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.dynamical.DrunkMan;
import ru.spbau.korovin.se.drunkman.dynamical.Lamp;
import ru.spbau.korovin.se.drunkman.dynamical.PoliceMan;
import ru.spbau.korovin.se.drunkman.field.Field;
import ru.spbau.korovin.se.drunkman.field.FieldObject;
import ru.spbau.korovin.se.drunkman.random.MathRandom;
import ru.spbau.korovin.se.drunkman.random.RandomGenerator;
import ru.spbau.korovin.se.drunkman.statical.Bottle;
import ru.spbau.korovin.se.drunkman.statical.LyingDrunkMan;
import ru.spbau.korovin.se.drunkman.statical.Pillar;
import ru.spbau.korovin.se.drunkman.statical.SleepingDrunkMan;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DrunkManTest {
    private Field mockField;
    private DrunkMan drunkMan;
    private RandomGenerator mockRandom;

    @Before
    public void createEnvironment() throws Exception {
        this.mockRandom = spy(new MathRandom());
        this.mockField = mock(Field.class);
        this.drunkMan = new DrunkMan(mockField, new Point(5,5), mockRandom);
    }

    @Test
    public void doNotMove() {
        // If all positions around are unavailable
        when(!mockField.isAvailable(Matchers.<Point>any())).thenReturn(false);

        drunkMan.act();

        // Drunk man should not move or drop bottles.
        verify(mockField, never()).changePosition(
                Matchers.<Point>any(), Matchers.<Point>any());
        verify(mockField, never()).placeObject(Matchers.<FieldObject>any());
    }

    @Test
    public void moveToFreePosition() {
        // If one position is free
        when(!mockField.isAvailable(Matchers.<Point>any()))
                .thenReturn(false, false, false, true);

        drunkMan.act();

        // Drunk man should change position and probably drop bottle
        verify(mockField).changePosition(
                Matchers.<Point>any(), Matchers.<Point>any());
        //verify(mockField, never()).placeObject(Matchers.<FieldObject>any());
    }

    @Test
    public void testBottleDrop() {
        when(mockRandom.nextDouble()).thenReturn(0.0);

        when(mockField.isAvailable(Matchers.<Point>any()))
            .thenReturn(true);

        Point bottlePosition = drunkMan.getPosition();

        drunkMan.act();

        verify(mockField).changePosition(
            Matchers.<Point>any(), Matchers.<Point>any());

        verify(mockField)
                .placeObject(refEq(new Bottle(mockField, bottlePosition)));
    }

    @Test
    public void testNoBottleDrop() {
        when(mockRandom.nextDouble()).thenReturn(1.0);

        when(mockField.isAvailable(Matchers.<Point>any()))
            .thenReturn(true);

        int stepsToCheck = 100;
        for(int i = 0; i < stepsToCheck; i++) {
            drunkMan.act();
        }

        verify(mockField, atMost(stepsToCheck)).changePosition(
                Matchers.<Point>any(), Matchers.<Point>any());

        verify(mockField, never()).placeObject(Matchers.<FieldObject>any());
    }

    @Test
    public void moveToPillar() {
        when(mockField.getObject(Matchers.<Point>any()))
                .thenReturn(new Pillar(mockField, new Point(7,7)));
        Point oldPosition = drunkMan.getPosition();
        drunkMan.act();
        verify(mockField).removeObject(drunkMan);
        assertFalse(drunkMan.isValid());
        verify(mockField).placeObject(
                refEq(new SleepingDrunkMan(mockField, oldPosition)));
    }

    @Test
    public void moveToBottle() {
        Point target = new Point(7,7);
        when(mockField.getObject(Matchers.<Point>any()))
                .thenReturn(new Bottle(mockField, target));
        drunkMan.act();
        verify(mockField).removeObject(drunkMan);
        assertFalse(drunkMan.isValid());
        verify(mockField).placeObject(
                refEq(new LyingDrunkMan(mockField, target)));
    }

    @Test
    public void moveToSleepingDrunkMan() {
        Point target = new Point(7,7);
        when(mockField.getObject(Matchers.<Point>any()))
                .thenReturn(new SleepingDrunkMan(mockField, target));
        Point oldPosition = drunkMan.getPosition();
        drunkMan.act();
        verify(mockField).removeObject(drunkMan);
        assertFalse(drunkMan.isValid());
        verify(mockField).placeObject(
                refEq(new SleepingDrunkMan(mockField, oldPosition)));
    }

    void moveToStatical(FieldObject object) {
        when(mockField.getObject(Matchers.<Point>any()))
                .thenReturn(object);
        Point oldPosition = drunkMan.getPosition();
        drunkMan.act();
        assertEquals(oldPosition, drunkMan.getPosition());
        assertTrue(drunkMan.isValid());
    }

    @Test
    public void moveToStaticalTest() {
        moveToStatical(new DrunkMan(mockField, new Point(7,7), mockRandom));
        moveToStatical(new LyingDrunkMan(mockField, new Point(7,7)));
        moveToStatical(new PoliceMan(mockField, mockField,
                new Point(0,15), new Point(7,7)));
        moveToStatical(new Lamp(mockField, new Point(7,7), 1));
    }
}