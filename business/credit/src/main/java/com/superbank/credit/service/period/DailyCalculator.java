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
public class DailyCalculator implements PeriodCalculator {

    private final CreditRates creditRates;

    public DailyCalculator(CreditRates creditRates) {
        this.creditRates = creditRates;
    }

    @Override
    public List<PaymentPeriod> calculate(CreditDto creditDto) {
        final LocalDate endDate = creditDto.startDate.plus(Period.of(creditDto.duration.years, creditDto.duration.months, creditDto.duration.days));
        final long days = ChronoUnit.DAYS.between(creditDto.startDate, endDate);
        final List<Double> sumsPerMonth = creditDto.rateType.calculateAmount(days, creditDto.sum, creditRates);
        return LongStream.range(0, days)
                .mapToObj(createPaymentPeriods(creditDto, sumsPerMonth))
                .collect(Collectors.toList());
    }

    private LongFunction<PaymentPeriod> createPaymentPeriods(final CreditDto creditDto, final List<Double> sumsPerMonth) {
        return day -> new PaymentPeriod(sumsPerMonth.get(Math.toIntExact(day)), creditDto.startDate.plusDays(day),
                                           creditDto.startDate.plusDays(day + 1), Status.FUTURE_PAYMENT);
    }
}
