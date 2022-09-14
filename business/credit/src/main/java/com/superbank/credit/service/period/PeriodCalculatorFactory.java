package com.superbank.credit.service.period;

import org.springframework.stereotype.Component;

@Component
public class PeriodCalculatorFactory {
    private final PeriodCalculator dailyCalculator;
    private final PeriodCalculator monthlyCalculator;

    public PeriodCalculatorFactory(final PeriodCalculator dailyCalculator,
                                   final PeriodCalculator monthlyCalculator) {
        this.dailyCalculator = dailyCalculator;
        this.monthlyCalculator = monthlyCalculator;
    }

    public PeriodCalculator calculator(final PeriodType periodType) {
        switch(periodType) {
            case DAILY: return dailyCalculator;
            case MONTHLY: return monthlyCalculator;
            default: throw new UnsupportedOperationException("Period type is not supported");
        }
    }
}
