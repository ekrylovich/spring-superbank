package com.superbank.rates.service;

import com.superbank.rates.model.CreditRates;
import org.springframework.stereotype.Component;

@Component
public class RateServiceClientFallback implements RateServiceClient{
    @Override
    public CreditRates requestRates() {
        return new CreditRates(1d, 1d);
    }
}
