package com.superbank.rates.model;

public class CreditRates {
    public final Double rateFixed;
    public final Double rateFloating;

    public CreditRates(Double rateFixed, Double rateFloating) {
        this.rateFixed = rateFixed;
        this.rateFloating = rateFloating;
    }
}
