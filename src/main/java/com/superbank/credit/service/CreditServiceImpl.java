package com.superbank.credit.service;

import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.dto.UserCreditDto;
import com.superbank.credit.model.Credit;
import com.superbank.credit.model.PaymentPeriod;
import com.superbank.credit.model.Status;
import com.superbank.credit.repository.CreditRepository;
import com.superbank.credit.service.period.PeriodCalculatorFactory;
import com.superbank.technical.exception.AlreadyPayedException;
import com.superbank.technical.exception.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        return creditRepository.findByUserId(userId).stream()
                               .map(this::mapCreditDto)
                               .collect(Collectors.toList());
    }

    @Override
    public void payNextPeriod(final Long creditId) {
        final Credit credit = creditRepository.findById(creditId)
                .orElseThrow(EntityNotFoundException::new);
        final PaymentPeriod nextPaymentPeriod = credit.getPaymentPeriods().stream()
                .filter(this::getPaymentPeriodPredicate)
                .min(Comparator.comparing(PaymentPeriod::getStartDate))
                .orElseThrow(AlreadyPayedException::new);
        nextPaymentPeriod.setStatus(Status.PAYED);
        creditRepository.save(credit);
    }

    private UserCreditDto mapCreditDto(final Credit credit) {
        final double remainingSum = credit.getPaymentPeriods()
                .stream()
                .filter(this::getPaymentPeriodPredicate)
                .mapToDouble(PaymentPeriod::getSumma)
                .sum();
        return UserCreditDto.createUserCreditDto(credit, remainingSum);
    }

    private boolean getPaymentPeriodPredicate(final PaymentPeriod paymentPeriod) {
        return !paymentPeriod.getStatus().payed();
    }
}
