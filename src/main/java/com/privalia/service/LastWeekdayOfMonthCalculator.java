package com.privalia.service;

import java.util.Calendar;
import java.util.Date;

public class LastWeekdayOfMonthCalculator {

    /**
     * Solution suggested in https://praveenlobo.com/blog/get-last-friday-of-the-month-in-java/
     */
    public Date calculate(int month, int year, int weekday) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month + 1, 1);

        int offsetNumber = (13 - weekday) % 7;
        calendar.add(Calendar.DAY_OF_MONTH, -(((offsetNumber + calendar.get(Calendar.DAY_OF_WEEK)) % 7) + 1));

        return calendar.getTime();
    }
}
