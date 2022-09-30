package com.superbank.credit.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.PositiveOrZero;

public class DurationDto {
    @Range(min = 0, max = 31, message = "{duration.days.minmax}")
    public final int days;
    @Range(min = 0, max = 12, message = "{duration.months.minmax}")
    public final int months;
    @PositiveOrZero
    public final int years;

    public DurationDto(final int days,
                       final int months,
                       final int years) {
        this.days = days;
        this.months = months;
        this.years = years;
    }
}
