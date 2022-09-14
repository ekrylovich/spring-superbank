package com.superbank.credit.dto;

import com.superbank.credit.model.Credit;

public class UserCreditDto {
    public final String title;
    public final String description;
    public final Long userId;
    public final Double remainingSum;

    private UserCreditDto(String title, String description, Long userId, Double remainingSum) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.remainingSum = remainingSum;
    }

    public static UserCreditDto createUserCreditDto(Credit credit, double remainingSum) {
        return new UserCreditDto(credit.getTitle(), credit.getDescription(), credit.getUserId(), remainingSum);
    }
}
