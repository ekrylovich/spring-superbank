package com.superbank.credit.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.superbank.credit.service.period.PeriodType;
import com.superbank.credit.service.period.RateType;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CreditDto {
    @NotEmpty(message = "{credit.title.empty}")
    @Size(max = 25, message = "{credit.title.size}")
    public final String title;
    @NotEmpty(message = "{credit.description.empty}")
    @Size(max = 250, message = "{credit.description.size}")
    public final String description;
    @NotNull(message = "{credit.userid.notnull}")
    public final Long userId;
    @Valid
    @NotNull(message = "{credit.duration.notnull}")
    public final DurationDto duration;
    @NotNull(message = "{credit.sum.notnull}")
    @Positive(message = "{credit.sum.positive}")
    public final Double sum;
    @NotNull(message = "{credit.rateType.notnull}")
    public final RateType rateType;
    @NotNull(message = "{credit.periodType.notnull}")
    public final PeriodType periodType;
    @NotNull(message = "{credit.startDate.notnull}")
    @FutureOrPresent(message = "{credit.startDate.future}")
    public final LocalDate startDate;

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
