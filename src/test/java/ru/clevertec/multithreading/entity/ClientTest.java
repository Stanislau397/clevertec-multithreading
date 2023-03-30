package ru.clevertec.multithreading.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ClientTest {

    private static final Integer NUMBER_OF_ELEMENTS = 100;
    private static Client client;
    private static Server server;

    @BeforeAll
    static void setUp() {
        client = new Client(NUMBER_OF_ELEMENTS);
        server = new Server();
        client.sendAllRequests(server);
    }

    @Test
    void checkSendAllRequestsShouldReturnAccumulator() {
        int expectedAccumulator = (1 + NUMBER_OF_ELEMENTS) * (NUMBER_OF_ELEMENTS / 2);

        int actualAccumulator = client.getAccumulator().get();

        assertThat(actualAccumulator).isEqualTo(expectedAccumulator);
    }

    @Test
    void checkSendAllRequestsShouldReturnResponsesListWithExpectedNumberOfElements() {
        int expectedSize = NUMBER_OF_ELEMENTS;

        int actualSize = server.getServerResponses().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    void checkSendAllRequestsShouldReturnRequestsListWithZeroElements() {
        int expectedSize = 0;

        int actualSize = client.getRequests().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }
}