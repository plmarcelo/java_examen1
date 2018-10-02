package com.privalia.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class LastWeekdayOfMonthCalculatorTest {

    @Test
    @Parameters(method = "parametersToCalculate")
    public void calculate(int month, int year, int weekday, int expectedDayOfMonth) {
        LastWeekdayOfMonthCalculator dateCalculator = new LastWeekdayOfMonthCalculator();

        Date calculatedDate = dateCalculator.calculate(month, year, weekday);

        assertNotNull(calculatedDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calculatedDate);

        assertEquals(expectedDayOfMonth, calendar.get(Calendar.DAY_OF_MONTH));
    }

    private Object[] parametersToCalculate() {
        return new Object[] {
                new Object[] { Calendar.JANUARY, 2018, Calendar.THURSDAY, 25},
                new Object[] { Calendar.FEBRUARY, 2018, Calendar.TUESDAY, 27},
                new Object[] { Calendar.OCTOBER, 2017, Calendar.SUNDAY, 29}
        };
    }
}