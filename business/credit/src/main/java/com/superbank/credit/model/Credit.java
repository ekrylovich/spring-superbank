package com.superbank.credit.model;

import javax.persistence.*;

import java.util.List;

@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "credit_id", nullable = false)
    private List<PaymentPeriod> paymentPeriods;

    public Credit(final String title,
                  final String description,
                  final Long userId,
                  final List<PaymentPeriod> paymentPeriods) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.paymentPeriods = paymentPeriods;
    }

    public Credit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public List<PaymentPeriod> getPaymentPeriods() {
        return paymentPeriods;
    }

    public void setPaymentPeriods(final List<PaymentPeriod> paymentPeriods) {
        this.paymentPeriods = paymentPeriods;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Credit credit = (Credit) object;

        if (!title.equals(credit.title)) {
            return false;
        }
        if (!description.equals(credit.description)) {
            return false;
        }
        if (!userId.equals(credit.userId)) {
            return false;
        }
        return paymentPeriods.equals(credit.paymentPeriods);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + paymentPeriods.hashCode();
        return result;
    }


    @Override
    public String
    toString() {
        return "Credit [" +
                "id=" + id +
                ", title=" + title +
                ", description=" + description +
                ", userId=" + userId +
                ", paymentPeriods=" + paymentPeriods +
                ']';
    }
}
