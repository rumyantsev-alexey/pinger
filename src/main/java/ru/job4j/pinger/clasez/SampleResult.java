package ru.job4j.pinger.clasez;

import java.util.Date;

public class SampleResult implements Result {

    @Override
    public boolean isValid(String address) {
        return true;
    }

    @Override
    public boolean proceed(String address, String result) {
        System.out.println(String.format("Print result %s at %s", address, new Date().toString()));
        System.out.println(result);
        return true;
    }
}
