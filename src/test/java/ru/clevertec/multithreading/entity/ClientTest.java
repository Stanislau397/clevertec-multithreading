package ru.clevertec.multithreading.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientTest {

    private static final Integer NUMBER_OF_ELEMENTS = 100;
    private Client client;

    @BeforeEach
    void setUp() {
        this.client = new Client(NUMBER_OF_ELEMENTS);
    }

    @Test
    void checkGenerateRequestShouldDecreaseSizeOfRequestsBy1() {
        int expectedSize = NUMBER_OF_ELEMENTS - 1;

        client.generateRequest(client.getRequests());

        int actualSize = client.getRequests().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    void checkGenerateRequestShouldBeNoNull() {
        Request actualRequest = client.generateRequest(client.getRequests());

        assertThat(actualRequest).isNotNull();
    }
}