package com.superbank.credit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;

@Entity
public class PaymentPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double summa;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;

    public PaymentPeriod(final Double summa,
                         final LocalDate startDate,
                         final LocalDate endDate,
                         final Status status) {
        this.summa = summa;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public PaymentPeriod() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(final Double summa) {
        this.summa = summa;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final PaymentPeriod that = (PaymentPeriod) object;

        if (!summa.equals(that.summa)) {
            return false;
        }
        if (!startDate.equals(that.startDate)) {
            return false;
        }
        if (!endDate.equals(that.endDate)) {
            return false;
        }
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = summa.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "PaymentPeriod [" +
                "id=" + id +
                ", summa=" + summa +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ']';
    }
}
