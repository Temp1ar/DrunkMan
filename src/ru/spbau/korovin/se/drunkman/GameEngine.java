package ru.spbau.korovin.se.drunkman;

import ru.spbau.korovin.se.drunkman.dynamical.DrunkMan;
import ru.spbau.korovin.se.drunkman.dynamical.DynamicObject;
import ru.spbau.korovin.se.drunkman.dynamical.Lamp;
import ru.spbau.korovin.se.drunkman.dynamical.PoliceMan;
import ru.spbau.korovin.se.drunkman.field.Field;
import ru.spbau.korovin.se.drunkman.random.MathRandom;
import ru.spbau.korovin.se.drunkman.statical.Pillar;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    public static void simulate() {
        Field field = new Field(15, 15);
        Pillar pillar = new Pillar(field, new Point(7, 7));
        Point pub = new Point(9, 0);
        Point policeStation = new Point(14, 3);
        Lamp lamp = new Lamp(field, new Point(9, 3), 3);

        field.placeObject(pillar);
        field.placeObject(lamp);

        List<DynamicObject> dynamic = new ArrayList<>();
        dynamic.add(lamp);

        for(int step = 0; step < 600; step++) {
            for(DynamicObject dO: dynamic) {
                if(dO.isValid()) {
                    dO.act();
                }
            }

            if(step % 20 == 0) {
                DrunkMan dm = new DrunkMan(field, pub, new MathRandom());
                if(field.placeObject(dm)) {
                    dynamic.add(dm);
                }
            }

            PoliceDispatcher policeDispatcher = PoliceDispatcher.getInstance();
            if(policeDispatcher.queueSize() > 0 && policeDispatcher.markedSize() == 0) {
                Point target = policeDispatcher.getVialatorPosition();
                PoliceMan policeMan = new PoliceMan(field, field,
                        policeStation, target);
                if(field.placeObject(policeMan)) {
                    dynamic.add(policeMan);
                    policeDispatcher.markVialator();
                }
            }

            if(step == 200 || step == 300 || step == 500) {
                field.draw();
                System.out.println();
            }
        }
    }
}
