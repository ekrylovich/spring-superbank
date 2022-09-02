package com.superbank.credit.service;

import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.dto.UserCreditDto;
import com.superbank.credit.model.Credit;
import com.superbank.credit.model.PaymentPeriod;
import com.superbank.credit.repository.CreditRepository;
import com.superbank.credit.service.period.PeriodCalculatorFactory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditServiceImpl implements CreditService{
    private final PeriodCalculatorFactory periodCalculatorFactory;
    private final CreditRepository creditRepository;

    public CreditServiceImpl(final PeriodCalculatorFactory periodCalculatorFactory, final CreditRepository creditRepository) {
        this.periodCalculatorFactory = periodCalculatorFactory;
        this.creditRepository = creditRepository;
    }


    @Override
    public void addCredit(CreditDto creditDto) {
        final List<PaymentPeriod> periods = periodCalculatorFactory
                .calculator(creditDto.periodType)
                .calculate(creditDto);
        final Credit credit = new Credit(creditDto.title, creditDto.description, creditDto.userId, periods);
        creditRepository.save(credit);
    }

    @Override
    public List<UserCreditDto> creditByUserId(Long userId) {
        return null;
    }

    @Override
    public void payNextPeriod(Long creditId) {

    }
}
