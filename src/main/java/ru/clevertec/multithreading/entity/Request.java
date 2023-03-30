package ru.clevertec.multithreading.entity;

public class Request {

    private final Integer value;

    public Request(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
