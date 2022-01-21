package com.github.ivansavelyev.votingsystem.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;

@UtilityClass
public class TimeUtil {

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static final LocalTime DEAD_LINE_TIME = LocalTime.of(11, 0, 0);

}
