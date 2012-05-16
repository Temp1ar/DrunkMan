package ru.spbau.korovin.se.drunkman.dynamic;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.field.Field;
import ru.spbau.korovin.se.drunkman.field.RectangularField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.spy;

public class FieldTest {
    private Field field;

    @Before
    public void createEnvironment() {
        this.field = spy(new RectangularField(15, 10));
    }

    @Test
    public void sizesOfField() {
        assertEquals(field.getWidth(), 15);
        assertEquals(field.getHeight(), 10);
    }

    @Test
    public void unavailableCells() {
        assertFalse(field.isAvailable(new Point(-1, -1)));
        assertFalse(field.isAvailable(new Point(16, 5)));
        assertFalse(field.isAvailable(new Point(5, 11)));
        assertFalse(field.isAvailable(new Point(16, 11)));
    }
}
