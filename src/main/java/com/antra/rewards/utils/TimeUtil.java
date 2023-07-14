package com.antra.rewards.utils;

import java.time.LocalDateTime;

public class TimeUtil {
    public static String getYearMonth(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.getYear() + "-" + dateTime.getMonth();
    }
}
