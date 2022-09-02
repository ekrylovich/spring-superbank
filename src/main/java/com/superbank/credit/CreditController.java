package com.superbank.credit;

import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.dto.UserCreditDto;
import com.superbank.credit.service.CreditService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/credit",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
public class CreditController {

    private CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public void addCredit(final @RequestBody CreditDto creditDto) {
        creditService.addCredit(creditDto);
    }

    @GetMapping(path = "/user/{userId}")
    public List<UserCreditDto> userCredits(final @PathVariable Long userId){
        return creditService.creditByUserId(userId);
    }

    @PutMapping(path = "/{creditId}")
    public void payNextPeriod(final @PathVariable Long creditId) {
        creditService.payNextPeriod(creditId);
    }
}
