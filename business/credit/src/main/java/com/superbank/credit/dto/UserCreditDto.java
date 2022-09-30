package com.superbank.credit.dto;

import com.superbank.credit.model.Credit;

public final class UserCreditDto {
    public final String title;
    public final String description;
    public final Long userId;
    public final Double remainingSum;

    private UserCreditDto(final String title,
                          final String description,
                          final Long userId,
                          final Double remainingSum) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.remainingSum = remainingSum;
    }

    public static UserCreditDto createUserCreditDto(final Credit credit, final double remainingSum) {
        return new UserCreditDto(credit.getTitle(), credit.getDescription(), credit.getUserId(), remainingSum);
    }
}
