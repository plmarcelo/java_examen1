package com.privalia.model;

import lombok.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SharePrice {

    private Date date;

    private BigDecimal closeValue;

    private BigDecimal openValue;

    public static SharePrice createFromStringList(List<String> stringList) throws ParseException {
        if (stringList.size() != 3) {
            throw new ParseException("Wrong list length", 0);
        }

        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date = dateFormat.parse(stringList.get(0));

        BigDecimal openValue = new BigDecimal(stringList.get(2).replaceAll(",", ""));
        BigDecimal closeValue = new BigDecimal(stringList.get(1).replaceAll(",", ""));

        return new SharePrice(date, closeValue, openValue);
    }
}