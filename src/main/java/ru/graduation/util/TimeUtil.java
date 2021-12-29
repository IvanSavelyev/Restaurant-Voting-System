package ru.graduation.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;

@UtilityClass
public class TimeUtil {

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static boolean isBetween(LocalTime lt) {
        return lt.compareTo(LocalTime.of(11, 0, 0)) >= 0 && lt.compareTo(LocalTime.of(0, 0, 0)) < 0;
    }
}
