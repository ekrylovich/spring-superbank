package com.superbank.credit;

import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.dto.UserCreditDto;
import com.superbank.credit.service.CreditService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/credit",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CreditController {

    private final CreditService creditService;

    public CreditController(final CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public void addCredit(final @Valid @RequestBody CreditDto creditDto) {
        creditService.addCredit(creditDto);
    }

    @GetMapping(path = "/user/{userId}")
    public List<UserCreditDto> userCredits(final @PathVariable Long userId) {
        return creditService.creditByUserId(userId);
    }

    @PutMapping(path = "/{creditId}")
    public void payNextPeriod(final @PathVariable Long creditId) {
        creditService.payNextPeriod(creditId);
    }
}
