package com.superbank.credit.service;

import com.superbank.credit.dto.CreditDto;
import com.superbank.credit.dto.DurationDto;
import com.superbank.credit.dto.UserCreditDto;
import com.superbank.credit.model.Credit;
import com.superbank.credit.model.PaymentPeriod;
import com.superbank.credit.model.Status;
import com.superbank.credit.repository.CreditRepository;
import com.superbank.credit.service.period.PeriodCalculatorFactory;
import com.superbank.credit.service.period.PeriodType;
import com.superbank.credit.service.period.RateType;
import com.superbank.technical.exception.AlreadyPayedException;
import com.superbank.technical.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.superbank.credit.service.CreditServiceImpl.CREDIT_NOT_FOUND;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditServiceTest {
    @InjectMocks
    private CreditServiceImpl creditService;
    @Mock
    private CreditRepository creditRepository;
    @Mock
    private PeriodCalculatorFactory periodCalculatorFactory;

    @Test
    public void addCredit() {
//        Given
        final PeriodType periodType = PeriodType.MONTHLY;
        final CreditDto creditDto = creditDto(periodType);
        final List<PaymentPeriod> paymentPeriods = paymentPeriods();
        final Credit credit = new Credit(creditDto.title, creditDto.description, creditDto.userId, paymentPeriods);
        when(periodCalculatorFactory.calculator(periodType)).thenReturn(new CalculatorStub(paymentPeriods));
//        When
        creditService.addCredit(creditDto);
//        Then
        verify(creditRepository, times(1)).save(credit);
        verify(periodCalculatorFactory, times(1)).calculator(periodType);
        verifyNoMoreInteractions(creditRepository, periodCalculatorFactory);
    }

    @Test
    public void creditByUserId() {
//        Given
        final Long userId = 110L;
        final List<PaymentPeriod> periods = paymentPeriods();
        periods.get(0).setStatus(Status.PAYED);
        final Double remainingSum = periods
                .stream()
                .filter(period -> !period.getStatus().payed())
                .mapToDouble(PaymentPeriod::getSumma)
                .sum();
        final Credit credit = new Credit("creditDto.title", "creditDto.description",
                userId, periods);
        when(creditRepository.findByUserId(userId)).thenReturn(singletonList(credit));
//        When
        List<UserCreditDto> userCreditDtos = creditService.creditByUserId(userId);
//        Then
        assertFalse(userCreditDtos.isEmpty());
        assertEquals(remainingSum, userCreditDtos.get(0).remainingSum);
        assertEquals("creditDto.title", userCreditDtos.get(0).title);
        assertEquals("creditDto.description", userCreditDtos.get(0).description);
        assertEquals(userId, userCreditDtos.get(0).userId);
        verify(creditRepository, times(1)).findByUserId(userId);
        verifyNoMoreInteractions(creditRepository);
    }

    @Test
    public void payNextPeriod() {
//        Given
        final Long creditId = 101L;
        final List<PaymentPeriod> periods = paymentPeriods();
        periods.get(0).setStatus(Status.PAYED);
        final Credit credit = new Credit("creditDto.title", "creditDto.description",
                101L, periods);
        when(creditRepository.findById(creditId)).thenReturn(Optional.of(credit));
//        When
        creditService.payNextPeriod(creditId);
//        Then
        assertEquals(Status.PAYED, periods.get(1).getStatus());
        verify(creditRepository, times(1)).findById(creditId);
        verify(creditRepository, times(1)).save(credit);
        verifyNoMoreInteractions(creditRepository);
    }

    @Test
    public void payNextPeriodNotFound() {
//        Given
        final Long creditId = 101L;
        when(creditRepository.findById(creditId)).thenReturn(Optional.empty());
//        When
        final EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> creditService.payNextPeriod(creditId),
                "Expected creditService.payNextPeriod to throw EntityNotFoundException");
//        Then
        assertTrue(thrown.getMessage().contains(CREDIT_NOT_FOUND));
    }

    @Test
    public void payNextPeriodAlreadyPayed() {
//        Given
        final Long creditId = 101L;
        final List<PaymentPeriod> periods = paymentPeriods();
        periods.forEach(period -> period.setStatus(Status.PAYED));
        final Credit credit = new Credit("creditDto.title", "creditDto.description",
                101L, periods);
        when(creditRepository.findById(creditId)).thenReturn(Optional.of(credit));
//        When/Then

        final AlreadyPayedException thrown = assertThrows(AlreadyPayedException.class,
                () -> creditService.payNextPeriod(creditId),
                "Expected creditService.payNextPeriod to throw AlreadyPayedException");

    }

    private CreditDto creditDto(final PeriodType periodType) {
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        return new CreditDto("CreditTitle", "CreditDescription",
                101L, durationDto, 100d, RateType.FIXED,
                periodType, LocalDate.now());
    }

    private List<PaymentPeriod> paymentPeriods() {
        return List.of(new PaymentPeriod(100d, LocalDate.now(), LocalDate.now().plusMonths(1), Status.FUTURE_PAYMENT),
                new PaymentPeriod(100d, LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(2), Status.FUTURE_PAYMENT),
                new PaymentPeriod(125d, LocalDate.now().plusMonths(2), LocalDate.now().plusMonths(3), Status.FUTURE_PAYMENT),
                new PaymentPeriod(150d, LocalDate.now().plusMonths(3), LocalDate.now().plusMonths(4), Status.FUTURE_PAYMENT));
    }
}
