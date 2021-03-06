package com.privalia.service;

import com.privalia.model.Investment;
import com.privalia.model.SharePrice;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Log4j
public class InvestmentListGenerator implements DateListGenerator<Investment> {

    public static final int SHARE_SCALE = 3;
    private List<SharePrice> sharePriceList;
    private PaymentDayListGenerator paymentDayListGenerator;
    private BigDecimal amountInvested;

    public InvestmentListGenerator(
            List<SharePrice> sharePriceList,
            PaymentDayListGenerator paymentDayListGenerator,
            BigDecimal amountInvested
    ) {
        this.sharePriceList = sharePriceList;
        this.paymentDayListGenerator = paymentDayListGenerator;
        this.amountInvested = amountInvested;
    }

    @Override
    public List<Investment> generate(Date startDate, Date endDate) {
        List<Date> paymentDayList = paymentDayListGenerator.generate(startDate, endDate);

        List<Investment> investmentList = new ArrayList<>();
        paymentDayList.stream()
                .forEach(paymentDay -> {
                    SharePrice nextSharePrice = this.sharePriceList.stream()
                            .filter(sharePrice -> sharePrice.getDate().after(paymentDay))
                            .findFirst()
                            .orElse(null);

                    if (nextSharePrice != null) {
                        BigDecimal sharesPurchased = this.amountInvested.divide(nextSharePrice.getOpenValue(), SHARE_SCALE, BigDecimal.ROUND_HALF_UP);
                        investmentList.add(new Investment(nextSharePrice.getDate(), sharesPurchased));
                    }
                });

        return investmentList;
    }
}
