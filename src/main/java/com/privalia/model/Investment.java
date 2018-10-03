package com.privalia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Investment {

    private Date date;

    private BigDecimal sharesPurchased;

}