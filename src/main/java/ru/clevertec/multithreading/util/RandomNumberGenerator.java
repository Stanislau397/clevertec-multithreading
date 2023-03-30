package ru.clevertec.multithreading.util;

import java.util.Random;

public class RandomNumberGenerator {

    private static final Random random = new Random();

    private RandomNumberGenerator() {

    }

    public static Integer generate(Integer min, Integer max) {
        return random.nextInt(max - min) + min;
    }
}
