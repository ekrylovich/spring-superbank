package com.superbank.credit.dto;

import com.superbank.credit.service.period.PeriodType;
import com.superbank.credit.service.period.RateType;
import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;

import static com.superbank.credit.tech.TestViolationChecker.checkViolation;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
    }

    @Test
    public void correct() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "CreditDescription",
                101L, durationDto, 100d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void titleEmpty() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("", "CreditDescription",
                101L, durationDto, 100d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.title.empty}");
    }

    @Test
    public void titleNull() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto(null, "CreditDescription",
                101L, durationDto, 100d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.title.empty}");
    }

    @Test
    public void titleMoreThan25() {
//        Given
        final String generatedString = RandomStringUtils.randomAlphanumeric(26);
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto(generatedString, "CreditDescription",
                101L, durationDto, 100d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.title.size}");
    }

    @Test
    public void descriptionEmpty() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "",
                101L, durationDto, 100d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.description.empty}");
    }

    @Test
    public void descriptionNull() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", null,
                101L, durationDto, 100d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.description.empty}");
    }

    @Test
    public void descriptionMoreThan250() {
//        Given
        final String generatedString = RandomStringUtils.randomAlphanumeric(251);
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", generatedString,
                101L, durationDto, 100d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.description.size}");
    }

    @Test
    public void userIdNull() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "generatedString",
                null, durationDto, 100d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.userid.notnull}");
    }

    @Test
    public void durationNull() {
//        Given
        final CreditDto creditDto = new CreditDto("CreditTitle", "generatedString",
                101L, null, 100d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.duration.notnull}");
    }

    @Test
    public void sumNull() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "generatedString",
                101L, durationDto, null, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.sum.notnull}");
    }

    @Test
    public void sumNegative() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "generatedString",
                101L, durationDto, -10d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.sum.positive}");
    }

    @Test
    public void rateTypeNull() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "generatedString",
                101L, durationDto, 10d, null,
                PeriodType.MONTHLY, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.rateType.notnull}");
    }

    @Test
    public void periodTypeNull() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "generatedString",
                101L, durationDto, 10d, RateType.FIXED,
                null, LocalDate.now());
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.periodType.notnull}");
    }

    @Test
    public void startDateNull() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "generatedString",
                101L, durationDto, 10d, RateType.FIXED,
                PeriodType.MONTHLY, null);
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.startDate.notnull}");
    }

    @Test
    public void startDatePast() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
        final CreditDto creditDto = new CreditDto("CreditTitle", "generatedString",
                101L, durationDto, 10d, RateType.FIXED,
                PeriodType.MONTHLY, LocalDate.now().minusDays(1));
//        When
        final Set<ConstraintViolation<CreditDto>> violations = validator.validate(creditDto);
//        Then
        checkViolation(violations, "{credit.startDate.future}");
    }
}
