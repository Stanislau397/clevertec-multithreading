package ru.clevertec.multithreading.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberGeneratorTest {

    private static final Integer MIN = 100;
    private static final Integer MAX = 1000;

    @Test
    void checkGenerateShouldReturnNumberGreaterOrEqualToMin() {
        int actualNumber = RandomNumberGenerator.generate(MIN, MAX);

        boolean condition = actualNumber >= MIN;

        assertThat(condition).isTrue();
    }

    @Test
    void checkGenerateShouldReturnNumberLessOrEqualToMax() {
        int actualNumber = RandomNumberGenerator.generate(MIN, MAX);

        boolean condition = actualNumber <= MAX;

        assertThat(condition).isTrue();
    }
}