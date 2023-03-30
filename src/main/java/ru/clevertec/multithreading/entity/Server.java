package ru.clevertec.multithreading.entity;

import lombok.SneakyThrows;
import ru.clevertec.multithreading.util.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

    private static final Integer MIN_DELAY = 100;
    private static final Integer MAX_DELAY = 1000;
    private final List<Integer> serverResponses = new ArrayList<>();
    private final Lock locker = new ReentrantLock();

    @SneakyThrows(InterruptedException.class)
    public Response processRequest(Request request) {
        int delay = RandomNumberGenerator.generate(MIN_DELAY, MAX_DELAY);
        TimeUnit.MILLISECONDS.sleep(delay);
        locker.lock();
        serverResponses.add(request.getValue());
        Response response = new Response(serverResponses.size());
        locker.unlock();
        return response;
    }

    public List<Integer> getServerResponses() {
        return serverResponses;
    }
}
