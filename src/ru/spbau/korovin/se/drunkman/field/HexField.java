package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;

import java.util.Arrays;
import java.util.List;

public class HexField extends Field {
    public HexField(int w, int h) {
        super(w, h);
    }

    public void draw() {
        for (int y = 0; y < w; y++) {
            char[] spaces = new char[y];
            Arrays.fill(spaces, ' ');
            System.out.print(spaces);
            for (int x = 0; x < h; x++) {
                Point p = new Point(x, y);
                FieldObject o = objectMap.get(p);
                if (o == null) {
                    System.out.print(empty + " ");
                } else {
                    System.out.print(o.getSymbol() + " ");
                }
            }
            System.out.println();
        }
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
