package ru.clevertec.multithreading.entity;

public class Response {

    private final Integer value;

    public Response(Integer responseValue) {
        this.value = responseValue;
    }

    public Integer getValue() {
        return value;
    }
}
