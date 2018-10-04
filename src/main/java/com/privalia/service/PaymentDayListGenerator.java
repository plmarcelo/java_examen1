package com.privalia.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PaymentDayListGenerator implements DateListGenerator<Date> {

    static Properties props = null;
    static InputStream input = null;

    private LastWeekdayOfMonthCalculator weekdayCalculator;

    public PaymentDayListGenerator(LastWeekdayOfMonthCalculator weekdayCalculator) {
        this.weekdayCalculator = weekdayCalculator;
    }

    @Override
    public List<Date> generate(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        List<Date> paymentDays = new ArrayList<>();

        while (startCalendar.before(endCalendar)) {
            Date lastWeekdayOfMonth = weekdayCalculator.calculate(
                    startCalendar.get(Calendar.MONTH),
                    startCalendar.get(Calendar.YEAR),
                    Integer.parseInt(props.getProperty("paymentDay"))
                    );

            paymentDays.add(lastWeekdayOfMonth);

            startCalendar.add(Calendar.MONTH, 1);
        }

        return paymentDays;
    }

    static {
        props = new Properties();

        try {
            input = PaymentDayListGenerator.class.getResourceAsStream("/config.properties");
            props.load(input);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
