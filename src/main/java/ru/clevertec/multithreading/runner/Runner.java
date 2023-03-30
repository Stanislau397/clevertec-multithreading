package ru.clevertec.multithreading.runner;

import ru.clevertec.multithreading.entity.Client;
import ru.clevertec.multithreading.entity.Server;

public class Runner {

    public static void main(String[] args) {
        int numberOfElements = 100;
        Client client = new Client(numberOfElements);
        Server server = new Server();
        client.sendAllRequests(server);
    }
}
