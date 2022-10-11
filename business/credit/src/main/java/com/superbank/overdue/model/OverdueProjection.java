package com.superbank.overdue.model;

import java.time.LocalDate;

public interface OverdueProjection {
    Double getRemainingSum();
    LocalDate getOverdueDate();
    Long getUserId();
}
