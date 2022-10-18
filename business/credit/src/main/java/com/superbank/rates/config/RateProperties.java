package com.superbank.rates.config;

import com.superbank.rates.model.CreditRateProvider;
import com.superbank.rates.model.LocalCreditRates;
import com.superbank.rates.model.RemoteCreditRates;
import com.superbank.rates.service.RateServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RateProperties {
    @Bean
    @Profile("dev")
    public CreditRateProvider devRates(final @Value("${credit.rate.fixed}") Double rateFixed,
                                       final @Value("${credit.rate.floating}") Double rateFloating) {
        return new LocalCreditRates(rateFixed, rateFloating);
    }

    @Bean
    @Profile("prod")
    public CreditRateProvider prodRates(final RateServiceClient rateServiceClient) {
        return new RemoteCreditRates(rateServiceClient);
    }
}
