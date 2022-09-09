package com.superbank.credit.service.period;

import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.model.PaymentPeriod;
import com.superbank.credit.model.Status;
import com.superbank.rates.model.CreditRates;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class MonthlyCalculator implements PeriodCalculator {

    private final CreditRates creditRates;

    public MonthlyCalculator(CreditRates creditRates) {
        this.creditRates = creditRates;
    }

    @Override
    public List<PaymentPeriod> calculate(CreditDto creditDto) {
        final LocalDate endDate = creditDto.startDate.plus(Period.of(creditDto.duration.years,
                                                                     creditDto.duration.months,
                                                                     creditDto.duration.days));
        final long months = ChronoUnit.MONTHS.between(creditDto.startDate, endDate);
        final List<Double> sumPerMonth = creditDto.rateType.calculateAmount(months, creditDto.sum, creditRates);
        return LongStream.range(0, months)
                .mapToObj(createPaymentPeriods(creditDto, sumPerMonth))
                .collect(Collectors.toList());

    }

    private LongFunction<PaymentPeriod> createPaymentPeriods(final CreditDto creditDto, final List<Double> sumsPerMonth) {
        return months -> new PaymentPeriod(sumsPerMonth.get(Math.toIntExact(months)), creditDto.startDate.plusMonths(months),
                                           creditDto.startDate.plusMonths(months + 1), Status.FUTURE_PAYMENT);
    }
}
