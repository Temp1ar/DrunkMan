package ru.spbau.korovin.se.drunkman;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 21:00
 */
public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return (41 * (41 + x) + y);
    }

    @Override
    public boolean equals( Object other ) {
        if (this == other) {
            return true;
        }

        boolean result = false;

        if (other instanceof Point) {
            Point that = (Point) other;
            result = that.canEqual(this)
                    && (this.x == that.x && this.y == that.y);
        }
        
        return result;

    }

    boolean canEqual(Object other) {
        return (other instanceof Point);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
