package com.superbank.credit.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static com.superbank.credit.tech.TestViolationChecker.checkViolation;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DurationDtoTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        ;
    }

    @Test
    public void correct() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 1);
//        When
        Set<ConstraintViolation<DurationDto>> violations = validator.validate(durationDto);
//        Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void daysNegative() {
//        Given
        final DurationDto durationDto = new DurationDto(-1, 1, 1);
//        When
        Set<ConstraintViolation<DurationDto>> violations = validator.validate(durationDto);
//        Then
        checkViolation(violations, "{duration.days.minmax}");
    }

    @Test
    public void daysMoreThan31() {
//        Given
        final DurationDto durationDto = new DurationDto(32, 1, 1);
//        When
        Set<ConstraintViolation<DurationDto>> violations = validator.validate(durationDto);
//        Then
        checkViolation(violations, "{duration.days.minmax}");
    }

    @Test
    public void monthsNegative() {
//        Given
        final DurationDto durationDto = new DurationDto(1, -1, 1);
//        When
        Set<ConstraintViolation<DurationDto>> violations = validator.validate(durationDto);
//        Then
        checkViolation(violations, "{duration.months.minmax}");
    }

    @Test
    public void monthsMoreThan31() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 13, 1);
//        When
        Set<ConstraintViolation<DurationDto>> violations = validator.validate(durationDto);
//        Then
        checkViolation(violations, "{duration.months.minmax}");
    }

    @Test
    public void yearsNegative() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, -1);
//        When
        Set<ConstraintViolation<DurationDto>> violations = validator.validate(durationDto);
//        Then
        checkViolation(violations, "{javax.validation.constraints.PositiveOrZero.message}");
    }

    @Test
    public void yearsZero() {
//        Given
        final DurationDto durationDto = new DurationDto(1, 1, 0);
//        When
        Set<ConstraintViolation<DurationDto>> violations = validator.validate(durationDto);
//        Then
        assertTrue(violations.isEmpty());
    }
}

