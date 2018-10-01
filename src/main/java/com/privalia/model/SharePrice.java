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
class SharePrice {

    private Date date;

    private BigDecimal openValue;

    private BigDecimal closeValue;

    public static SharePrice createFromStringList(List<String> stringList) throws ParseException {
        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date = dateFormat.parse(stringList.get(0));

        BigDecimal openValue = new BigDecimal(stringList.get(1).replaceAll(",", ""));
        BigDecimal closeValue = new BigDecimal(stringList.get(2).replaceAll(",", ""));

        return new SharePrice(date, openValue, closeValue);
    }
}