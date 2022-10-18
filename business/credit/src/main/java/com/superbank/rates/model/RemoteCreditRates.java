package com.superbank.rates.model;

import com.superbank.rates.service.RateServiceClient;

public class RemoteCreditRates implements CreditRateProvider{
    private final RateServiceClient rateServiceClient;

    public RemoteCreditRates(final RateServiceClient rateServiceClient) {
        this.rateServiceClient = rateServiceClient;
    }

    @Override
    public CreditRates rates() {
        return rateServiceClient.requestRates();
    }
}
