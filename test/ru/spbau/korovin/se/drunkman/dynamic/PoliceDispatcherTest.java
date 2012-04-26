package ru.spbau.korovin.se.drunkman.dynamic;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.PoliceDispatcher;
import ru.spbau.korovin.se.drunkman.characters.statical.LyingDrunkMan;
import ru.spbau.korovin.se.drunkman.field.Field;

import static org.junit.Assert.assertEquals;

public class PoliceDispatcherTest {
    PoliceDispatcher policeDispatcher;
    LyingDrunkMan lyingMan;

    @Before
    public void createEnvironment() {
        policeDispatcher = PoliceDispatcher.getInstance();
        Field field = new Field(15, 15);
        lyingMan = new LyingDrunkMan(field, new Point(5, 5));
    }

    @Test
    public void testAddViolator() {
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
        policeDispatcher.addVialator(lyingMan);
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
        policeDispatcher.addVialator(lyingMan);
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
        policeDispatcher.addVialator(lyingMan);
        policeDispatcher.markVialator();
        policeDispatcher.popVialator();
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
    }

    @Test
    public void testGetViolator() {
        assertEquals(0, policeDispatcher.queueSize());
        assertEquals(0, policeDispatcher.markedSize());
        policeDispatcher.addVialator(lyingMan);
        assertEquals(lyingMan.getPosition(),
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
