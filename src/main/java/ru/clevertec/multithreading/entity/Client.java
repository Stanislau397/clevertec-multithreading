package ru.clevertec.multithreading.entity;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Client {

    private final List<Integer> requests;
    private final AtomicInteger accumulator;
    private final Random random;
    private final Lock locker;
    private final ExecutorService executorService;
    private final Integer requestsSize;

    public Client(int numberOfElements) {
        this.requests = IntStream.rangeClosed(1, numberOfElements)
                .boxed()
                .collect(Collectors.toList());
        this.accumulator = new AtomicInteger(0);
        this.executorService = Executors.newFixedThreadPool(numberOfElements);
        this.requestsSize = numberOfElements;
        this.random = new Random();
        this.locker = new ReentrantLock();
    }

    public int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public Request generateRequest(List<Integer> requests) {
        locker.lock();
        int randomNumber = generateRandomNumber(0, requests.size());
        int removedValue = requests.remove(randomNumber);
        Request request = new Request(removedValue);
        locker.unlock();
        return request;
    }

    @SneakyThrows(InterruptedException.class)
    public void sendAllRequests(Server server) {
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < requestsSize; i++) {
            Callable<Integer> sendRequestCallable = () -> {
                Request request = generateRequest(requests);
                Response serverResponse = server.processRequest(request);
                return accumulator.addAndGet(serverResponse.getValue());
            };
            tasks.add(sendRequestCallable);
        }
        executorService.invokeAll(tasks);
        executorService.shutdown();
    }

    public List<Integer> getRequests() {
        return requests;
    }

    public AtomicInteger getAccumulator() {
        return accumulator;
    }
}
