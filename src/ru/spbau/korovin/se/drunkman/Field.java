package ru.spbau.korovin.se.drunkman;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Temp1ar
 * Date: 19.02.12
 * Time: 15:40
 */
public class Field {
    final private int w;
    final private int h;
    final private Map<Point, FieldObject> objectMap;
    final private char empty = '0';

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

    public boolean isAvailable(Point p) {
        return p.x >= 0 && p.x < w && p.y >= 0 && p.y < h;
    }

    private boolean isFreeToSpawn(Point p) {
         return (getObject(p) == null || Bottle.class.isInstance(getObject(p)));
    }

    public boolean isFree(Point p) {
        return (getObject(p) == null);
    }

    public FieldObject getObject(Point p) {
        return objectMap.get(p);
    }

    public boolean placeObject(FieldObject object) {
        if(isAvailable(object.getPosition())
            && isFreeToSpawn(object.getPosition())) {
            objectMap.put(object.getPosition(), object);
            return true;
        }
        return false;
    }

    public void removeObject(FieldObject object) {
        objectMap.remove(object.getPosition());
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public void changePosition(Point position, Point to) {
        if(position != to) {
            objectMap.put(to, objectMap.get(position));
            objectMap.remove(position);
        }
    }
}
