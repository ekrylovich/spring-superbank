package com.superbank.overdue.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class OverdueDto {
    private Double    remainingSum;
    private LocalDate overdueDate;
    private Long      userId;

    @JsonCreator
    public OverdueDto(final @JsonProperty("remainingSum") Double remainingSum,
                      final @JsonProperty("overdueDate") LocalDate overdueDate,
                      final @JsonProperty("userId") Long userId) {
        this.remainingSum = remainingSum;
        this.overdueDate = overdueDate;
        this.userId = userId;
    }

    public Double getRemainingSum() {
        return remainingSum;
    }

    public void setRemainingSum(Double remainingSum) {
        this.remainingSum = remainingSum;
    }

    public LocalDate getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(LocalDate overdueDate) {
        this.overdueDate = overdueDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OverdueDto [" +
                "remainingSum=" + remainingSum +
                ", overdueDate=" + overdueDate +
                ", userId=" + userId +
                ']';
    }
}
