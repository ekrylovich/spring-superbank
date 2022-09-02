package com.superbank.credit.dto;

public class UserCreditDto {
    public final String title;
    public final String description;
    public final Long userId;
    public final Double remainingSum;

    public UserCreditDto(String title, String description, Long userId, Double remainingSum) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.remainingSum = remainingSum;
    }
}
