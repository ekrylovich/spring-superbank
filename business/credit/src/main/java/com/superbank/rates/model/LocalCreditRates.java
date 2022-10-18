package com.superbank.rates.model;

public class LocalCreditRates implements CreditRateProvider{
    private final Double rateFixed;
    private final Double rateFloating;

    public LocalCreditRates(final Double rateFixed, final Double rateFloating) {
        this.rateFixed = rateFixed;
        this.rateFloating = rateFloating;
    }

    @Override
    public CreditRates rates() {
        return new CreditRates(rateFixed, rateFloating);
    }
}
