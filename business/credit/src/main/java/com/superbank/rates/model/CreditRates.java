package com.superbank.rates.model;

public class CreditRates {
    public final Double rateFixed;
    public final Double rateFloating;

    public CreditRates(final Double rateFixed, final Double rateFloating) {
        this.rateFixed = rateFixed;
        this.rateFloating = rateFloating;
    }
}
