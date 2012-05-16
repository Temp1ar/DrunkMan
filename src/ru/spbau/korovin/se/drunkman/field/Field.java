package ru.spbau.korovin.se.drunkman.field;

import ru.spbau.korovin.se.drunkman.Point;
import ru.spbau.korovin.se.drunkman.characters.statical.Bottle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Field implements FieldInformation, FieldManipulator {
    final int w;
    final int h;
    final Map<Point, FieldObject> objectMap;

    public Field(int w, int h) {
        this.w = w;
        this.h = h;

        objectMap = new HashMap<>();
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

    public abstract List<Point> getDirections();

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
