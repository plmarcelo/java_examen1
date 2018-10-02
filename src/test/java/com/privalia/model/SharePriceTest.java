package com.privalia.model;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SharePriceTest {

    @Test
    public void objectShouldBeCreatedFromStringList() throws ParseException {
        List<String> stringList = Arrays.asList("28-dic-2017", "29.17", "29.6");

        SharePrice sharePrice = SharePrice.createFromStringList(stringList);

        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date = dateFormat.parse(stringList.get(0));

        assertEquals(date, sharePrice.getDate());
        assertEquals(stringList.get(1), sharePrice.getOpenValue().toString());
        assertEquals(stringList.get(2), sharePrice.getCloseValue().toString());
    }

    @Test(expected = ParseException.class)
    public void aParseExceptionIsThrownIfStringListLengthIsWrong() throws ParseException {
        List<String> stringList = Arrays.asList("28-dic-2017", "29.17");

        SharePrice.createFromStringList(stringList);
    }
}