package com.superbank.credit.dto;

public class DurationDto {
    public final int days;
    public final int months;
    public final int years;

    public DurationDto(int days, int months, int years) {
        this.days = days;
        this.months = months;
        this.years = years;
    }
}
