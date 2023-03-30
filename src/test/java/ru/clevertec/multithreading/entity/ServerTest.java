package ru.clevertec.multithreading.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ServerTest {

    private static final Integer MIN_DELAY = 100;
    private static final Integer MAX_DELAY = 1000;
    private Server server;

    @BeforeEach
    void setUp() {
        this.server = new Server();
    }

    @Test
    void checkAfterProcessRequestResponsesListSizeShouldEqualTo1() {
        int expectedSize = 1;
        Request request = new Request(1);

        server.processRequest(request);

        int actualSize = server.getServerResponses().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    void checkAfterProcessRequestResponsesListShouldContainGivenValue() {
        Request request = new Request(1);
        int expectedValue = request.getValue();

        server.processRequest(request);

        int actualValue = server.getServerResponses().get(0);

        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void checkGenerateDelayShouldReturnValueGreaterOrEqualToMin() {
        int delay = server.generateDelay(MIN_DELAY, MAX_DELAY);

        boolean condition = delay >= MIN_DELAY;

        assertThat(condition).isTrue();
    }

    @Test
    void checkGenerateDelayShouldReturnValueLessOrEqualToMax() {
        int delay = server.generateDelay(MIN_DELAY, MAX_DELAY);

        boolean condition = delay <= MAX_DELAY;

        assertThat(condition).isTrue();
    }
}