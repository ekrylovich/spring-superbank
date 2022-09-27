package com.superbank.credit.tech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class TestViolationChecker {

    public static <T> void checkViolation(final Set<ConstraintViolation<T>> violations, final String messageTemplate) {
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals(messageTemplate, violations.iterator().next().getMessageTemplate());
    }
}
