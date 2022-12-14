package org.superbank.rate.controller;

import static org.superbank.rate.RateApplication.API_VERSION;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.superbank.rate.dto.CreditRatesDto;

@RestController
@RequestMapping(path = API_VERSION +"rate/",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
public class RateController {

    @GetMapping(path="/credit")
    public CreditRatesDto creditRates(){
        return new CreditRatesDto(0.489, 0.13);
    }
}
