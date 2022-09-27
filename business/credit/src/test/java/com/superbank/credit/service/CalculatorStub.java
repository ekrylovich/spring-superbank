package com.superbank.credit.service;

import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.model.PaymentPeriod;
import com.superbank.credit.service.period.PeriodCalculator;

import java.util.List;

public class CalculatorStub implements PeriodCalculator {
    private final List<PaymentPeriod> paymentPeriods;

    public CalculatorStub(List<PaymentPeriod> paymentPeriods) {
        this.paymentPeriods = paymentPeriods;
    }

    @Override
    public List<PaymentPeriod> calculate(CreditDto creditDto) {
        return paymentPeriods;
    }
}
