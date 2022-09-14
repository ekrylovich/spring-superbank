package org.superbank.rate.dto;

public class CreditRatesDto {
    public final Double rateFixed;
    public final Double rateFloating;

    public CreditRatesDto(Double rateFixed, Double rateFloating) {
        this.rateFixed = rateFixed;
        this.rateFloating = rateFloating;
    }
}
