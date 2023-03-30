package ru.clevertec.multithreading.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {

    private static final Integer NUMBER_OF_ELEMENTS = 100;
    private static List<Integer> requests;
    private static Client client;
    private static Server server;

    @BeforeAll
    static void setUp() {
        client = new Client(NUMBER_OF_ELEMENTS);
        server = new Server();
        requests = new ArrayList<>(client.getRequests());
        client.sendAllRequests(server);
    }

    @Test
    void checkAccumulatorShouldBeEqualToExpectedValue() {
        int expectedValue = (1 + NUMBER_OF_ELEMENTS) * (NUMBER_OF_ELEMENTS / 2);

        int actualValue = client.getAccumulator().get();

        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void checkServerResponsesListSizeShouldBeEqualToNumberOfElements() {
        int expectedSize = NUMBER_OF_ELEMENTS;

        int actualSize = server.getServerResponses().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    void checkRequestsListSizeShouldBeZero() {
        int expectedSize = 0;

        int actualSize = client.getRequests().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    void checkServerResponsesListShouldContainAllRequests() {

        List<Integer> serverResponses = server.getServerResponses();

        assertThat(serverResponses).containsAll(requests);
    }
}
