package ru.graduation.util;

import java.time.LocalTime;

public class TimeUtil {

    public static boolean isBetween(LocalTime lt) {
        return lt.compareTo(LocalTime.of(23, 0, 0)) >= 0 && lt.compareTo(LocalTime.of(0, 0, 0)) < 0;
    }
}
