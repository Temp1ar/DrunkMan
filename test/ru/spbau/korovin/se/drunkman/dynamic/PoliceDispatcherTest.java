package ru.spbau.korovin.se.drunkman.dynamic;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.PoliceDispatcher;
import ru.spbau.korovin.se.drunkman.characters.dynamical.DrunkMan;
import ru.spbau.korovin.se.drunkman.field.Field;
import ru.spbau.korovin.se.drunkman.field.RectangularField;
import ru.spbau.korovin.se.drunkman.random.MathRandom;

import static org.junit.Assert.assertEquals;

public class PoliceDispatcherTest {
    PoliceDispatcher policeDispatcher;
    DrunkMan drunkMan;

    @Before
    public void createEnvironment() {
        policeDispatcher = PoliceDispatcher.getInstance();
        Field field = new RectangularField(15, 15);
        drunkMan = new DrunkMan(field, new Point(5, 5), new MathRandom());
    }

    @Test
    public void testAddViolator() {
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
        policeDispatcher.addVialator(drunkMan);
        assertEquals(1, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
        // Cleaning up
        policeDispatcher.markVialator();
        policeDispatcher.popVialator();
    }

    @Test
    public void testMarkViolator() {
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
        policeDispatcher.addVialator(drunkMan);
        policeDispatcher.markVialator();
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(1, policeDispatcher.markedSize());
        // Cleaning up
        policeDispatcher.popVialator();
    }

    @Test
    public void testPopViolator() {
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
        policeDispatcher.addVialator(drunkMan);
        policeDispatcher.markVialator();
        policeDispatcher.popVialator();
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
    }

    @Test
    public void testGetViolator() {
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
        policeDispatcher.addVialator(drunkMan);
        assertEquals(drunkMan.getPosition(),
                policeDispatcher.getVialatorPosition());
        policeDispatcher.markVialator();
        policeDispatcher.popVialator();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetViolatorFromEmpty() {
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
        policeDispatcher.getVialatorPosition();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPopViolatorFromEmpty() {
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
        policeDispatcher.popVialator();
    }
}
