package com.privalia.service;

import java.util.Date;
import java.util.List;

public interface DateListGenerator<T> {
    public List<T> generate(Date startDate, Date endDate);
}
