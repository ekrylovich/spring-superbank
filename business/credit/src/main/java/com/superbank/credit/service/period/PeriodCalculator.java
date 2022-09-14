package com.superbank.credit.service.period;

import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.model.PaymentPeriod;

import java.util.List;

public interface PeriodCalculator {
    List<PaymentPeriod> calculate(final CreditDto creditDto);
}
