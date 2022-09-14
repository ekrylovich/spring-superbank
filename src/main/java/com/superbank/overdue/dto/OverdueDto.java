package com.superbank.overdue.dto;

import java.time.LocalDate;

public class OverdueDto {
    public final Double remainingSum;
    public final LocalDate overdueDate;
    public final Long userId;

    public OverdueDto(Double remainingSum, LocalDate overdueDate, Long userId) {
        this.remainingSum = remainingSum;
        this.overdueDate = overdueDate;
        this.userId = userId;
    }
}
