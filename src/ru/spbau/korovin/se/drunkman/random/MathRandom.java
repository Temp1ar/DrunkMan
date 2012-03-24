package ru.spbau.korovin.se.drunkman.random;

public class MathRandom implements RandomGenerator {
    @Override
    public double nextDouble() {
        return Math.random();
    }
}
