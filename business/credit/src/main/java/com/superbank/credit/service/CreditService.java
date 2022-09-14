package com.superbank.credit.service;

import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.dto.UserCreditDto;

import java.util.List;

public interface CreditService {

    void addCredit(final CreditDto creditDto);

    List<UserCreditDto> creditByUserId(final Long userId);

    void payNextPeriod(final Long creditId);

}
