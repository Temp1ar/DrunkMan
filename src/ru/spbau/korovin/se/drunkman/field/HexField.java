package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;

import java.util.Arrays;
import java.util.List;

public class HexField extends Field {
    public HexField(int w, int h) {
        super(w, h);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < w; y++) {
            char[] spaces = new char[y];
            Arrays.fill(spaces, ' ');
            output.append(spaces);
            for (int x = 0; x < h; x++) {
                Point p = new Point(x, y);
                FieldObject o = objectMap.get(p);
                if (o == null) {
                    output.append('0');
                } else {
                    output.append(o.getSymbol());
                }
                output.append(' ');
            }
            output.append('\n');
        }

        return output.toString();
    }

    @Override
    public List<Point> getDirections() {
        return Arrays.asList(
                new Point(0, -1), // go up left
                new Point(1, -1), // go up right
                new Point(1, 0), // go right
                new Point(0, 1), // go down right
                new Point(-1, 1), // go down left
                new Point(-1, 0)  // go left
        );
    }
}
