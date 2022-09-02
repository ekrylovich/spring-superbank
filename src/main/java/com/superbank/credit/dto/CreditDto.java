package com.superbank.credit.dto;

import com.superbank.credit.service.period.PeriodType;
import com.superbank.credit.service.period.RateType;

import java.time.LocalDate;

public class CreditDto {
    public final String      title;
    public final String      description;
    public final Long        userId;
    public final DurationDto duration;
    public final Double     sum;
    public final RateType   rateType;
    public final PeriodType periodType;
    public final LocalDate  startDate;

    public CreditDto(final String title,
                     final String description,
                     final Long userId,
                     final DurationDto duration,
                     final Double sum,
                     final RateType rateType,
                     final PeriodType periodType,
                     final LocalDate startDate) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.duration = duration;
        this.sum = sum;
        this.rateType = rateType;
        this.periodType = periodType;
        this.startDate = startDate;
    }
}
