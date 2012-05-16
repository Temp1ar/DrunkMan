package ru.spbau.korovin.se.drunkman.dynamic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.PoliceDispatcher;
import ru.spbau.korovin.se.drunkman.characters.dynamical.DrunkMan;
import ru.spbau.korovin.se.drunkman.characters.dynamical.PoliceMan;
import ru.spbau.korovin.se.drunkman.field.FieldManipulator;
import ru.spbau.korovin.se.drunkman.field.RectangularField;
import ru.spbau.korovin.se.drunkman.random.MathRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class PoliceManTest {
    private FieldManipulator mockField;
    private PoliceMan policeMan;
    private DrunkMan violator;
    private Point station;

    @Before
    public void createEnvironment() throws Exception {
        this.mockField = spy(new RectangularField(15, 15));
        this.station = new Point(0, 0);
        this.violator =
                spy(new DrunkMan(mockField, new Point(0, 2), new MathRandom()));
        when(violator.getState()).thenReturn(DrunkMan.State.LYING);

        mockField.placeObject(violator);
        PoliceDispatcher.getInstance()
                .addVialator(violator);
        this.policeMan = new PoliceMan(mockField,
                station, violator.getPosition());
    }

    @Test
    public void doNotMove() {
        // Police man stands only if there is no path to target
        // Lock him in the corner :)

        when(mockField.isFree(new Point(0, 1)))
                .thenReturn(false);
        when(mockField.isFree(new Point(1, 0)))
                .thenReturn(false);

        Point oldPosition = policeMan.getPosition();
        policeMan.act();

        assertEquals(oldPosition, policeMan.getPosition());
    }

    @Test
    public void simpleStep() {
        Point freeCell = new Point(0, 1);
        when(mockField.isFree(freeCell))
                .thenReturn(true);
        when(mockField.isFree(new Point(1, 0)))
                .thenReturn(false);

        Point oldPosition = policeMan.getPosition();
        policeMan.act();

        verify(mockField).changePosition(refEq(oldPosition), refEq(freeCell));
        assertEquals(freeCell, policeMan.getPosition());
    }

    @Test
    public void meetLyingDrunkMan() {
        policeMan.act();

        Point oldPosition = policeMan.getPosition();
        policeMan.act();

        verify(mockField).removeObject(violator);
        Point violatorPosition = violator.getPosition();
        verify(mockField).changePosition(
                refEq(oldPosition), refEq(violatorPosition));
        assertEquals(violator.getPosition(), policeMan.getPosition());
    }

    @Test
    public void returnedToBase() {
        // Simulation returning to base state
        for (int i = 4; i > 0; i--) {
            policeMan.act();
        }

        verify(mockField, times(4)).changePosition(
                Matchers.<Point>any(), Matchers.<Point>any());
        assertEquals(policeMan.getPosition(), station);
        verify(mockField).removeObject(policeMan);
        assertFalse(policeMan.isValid());
    }
}
