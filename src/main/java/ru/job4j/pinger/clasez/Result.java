package ru.job4j.pinger.clasez;

import java.io.Serializable;

public interface Result extends Serializable {
    boolean isValid(String address);
    boolean proceed(String address, String result);
}
