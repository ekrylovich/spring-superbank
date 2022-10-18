package com.superbank.rates.service;

import com.superbank.rates.model.CreditRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("rate-service")
public interface RateServiceClient {
    @GetMapping(path = "api/v1/rate/credit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CreditRates requestRates();
}
