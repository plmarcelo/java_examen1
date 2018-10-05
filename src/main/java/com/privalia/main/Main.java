package com.privalia.main;

import com.privalia.model.Investment;
import com.privalia.model.SharePrice;
import com.privalia.service.InvestmentListGenerator;
import com.privalia.service.LastWeekdayOfMonthCalculator;
import com.privalia.service.PaymentDayListGenerator;
import com.privalia.service.SharesReader;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Main {
    private static Properties props = null;

    public static void main(String[] args) throws ParseException {
        Float rawAmountInvested = Float.parseFloat(props.getProperty("amountInvested"));
        Float brokerPercent = Float.parseFloat(props.getProperty("brokerPercent"));
        Float brokerProfit = (brokerPercent / 100.0f) * rawAmountInvested;
        Float netInvestment = rawAmountInvested - brokerProfit;

        SharesReader sharesReader = new SharesReader();
        List<SharePrice> sharePriceList = sharesReader.read(props.getProperty("sharesFile"));

        InvestmentListGenerator investmentsListGenerator = new InvestmentListGenerator(
                sharePriceList,
                new PaymentDayListGenerator(new LastWeekdayOfMonthCalculator()),
                new BigDecimal(Float.toString(netInvestment))
        );

        DateFormat dateFormat = DateFormat.getDateInstance();
        Date startDate = dateFormat.parse(props.getProperty("startInvestAt"));
        Date endDate = dateFormat.parse(props.getProperty("sellSharesAt"));
        List<Investment> investmentsList = investmentsListGenerator.generate(startDate, endDate);

        BigDecimal totalShares = investmentsList.stream().map(Investment::getSharesPurchased)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        SharePrice sellDaySharePrice = sharePriceList.stream()
                .filter(sharePrice -> endDate.equals(sharePrice.getDate()))
                .findFirst()
                .orElse(null);

        if (sellDaySharePrice != null) {
            BigDecimal totalProfit = sellDaySharePrice.getCloseValue().multiply(totalShares).setScale(3, BigDecimal.ROUND_HALF_UP);
            System.out.println("Total profit: " + totalProfit);
        } else {
            System.out.println("No sell price found on sell day");
        }
    }


    static {
        props = new Properties();

        try {
            InputStream input = PaymentDayListGenerator.class.getResourceAsStream("/config.properties");
            props.load(input);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
