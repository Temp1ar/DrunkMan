package ru.spbau.korovin.se.drunkman;

import ru.spbau.korovin.se.drunkman.characters.dynamical.*;
import ru.spbau.korovin.se.drunkman.characters.statical.Pillar;
import ru.spbau.korovin.se.drunkman.field.Field;
import ru.spbau.korovin.se.drunkman.field.HexField;
import ru.spbau.korovin.se.drunkman.random.MathRandom;

import java.util.ArrayList;
import java.util.List;

class GameEngine {
    public static void simulate(boolean hexField) {
        Field field = hexField ? new HexField(15, 15) : new Field(15, 15);

        Pillar pillar = new Pillar(field, new Point(7, 7));
        field.placeObject(pillar);

        Point pub = new Point(9, 0);
        Point policeStation = new Point(14, 3);

        Lamp lamp = new Lamp(field, new Point(9, 3), 3);
        field.placeObject(lamp);


        Beggar beggar = new Beggar(field, field, new Point(4, 14));
        field.placeObject(beggar);

        List<DynamicObject> dynamic = new ArrayList<>();
        dynamic.add(lamp);
        dynamic.add(beggar);

        for (int step = 0; step < 600; step++) {
            for (DynamicObject dO : dynamic) {
                if (dO.isValid()) {
                    dO.act();
                }
            }

            if (step % 20 == 0) {
                DrunkMan dm = new DrunkMan(field, pub, new MathRandom());
                if (field.placeObject(dm)) {
                    dynamic.add(dm);
                }
            }

            PoliceDispatcher policeDispatcher = PoliceDispatcher.getInstance();
            if (policeDispatcher.queueSize() > 0 && policeDispatcher.markedSize() == 0) {
                Point target = policeDispatcher.getVialatorPosition();
                PoliceMan policeMan = new PoliceMan(field, field,
                        policeStation, target);
                if (field.placeObject(policeMan)) {
                    dynamic.add(policeMan);
                    policeDispatcher.markVialator();
                }
            }

            if (step == 200 || step == 300 || step == 500) {
                field.draw();
                System.out.println();
            }
        }
    }
}
