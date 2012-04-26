package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;

import java.util.*;

public class Field implements FieldInformation, FieldManipulator {
    final int w;
    final int h;
    final Map<Point, FieldObject> objectMap;
    final char empty = '0';

    public Field(int w, int h) {
        this.w = w;
        this.h = h;

        objectMap = new HashMap<>();
    }

    public void draw() {
        for (int y = 0; y < w; y++) {
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
    public boolean isAvailable(Point p) {
        return p.x >= 0 && p.x < w && p.y >= 0 && p.y < h;
    }

    private boolean isFreeToSpawn(Point p) {
        return getObject(p) == null
                || (getObject(p) instanceof Bottle);
    }

    public boolean isFree(Point p) {
        return (getObject(p) == null);
    }

    @Override
    public List<Point> getDirections() {
        return Arrays.asList(
                new Point(0, 1), // go up
                new Point(1, 0), // go right
                new Point(0, -1), // go down
                new Point(-1, 0)  // go left
        );
    }

    @Override
    public FieldObject getObject(Point p) {
        return objectMap.get(p);
    }

    @Override
    public boolean placeObject(FieldObject object) {
        if (isAvailable(object.getPosition())
                && isFreeToSpawn(object.getPosition())) {
            objectMap.put(object.getPosition(), object);
            return true;
        }
        return false;
    }

    @Override
    public void removeObject(FieldObject object) {
        objectMap.remove(object.getPosition());
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    @Override
    public void changePosition(Point position, Point to) {
        if (position != to) {
            objectMap.put(to, objectMap.get(position));
            objectMap.remove(position);
        }
    }

    public List<Point> getAvailableNeighbours(Point position) {
        List<Point> possibleMoves = new ArrayList<>();
        List<Point> possibleDirections = new ArrayList<>(getDirections());

        for (Point direction : possibleDirections) {
            if (isAvailable(position.plus(direction))) {
                possibleMoves.add(position.plus(direction));
            }
        }

        return possibleMoves;
    }
}
