package com.superbank.credit.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                id;
    private String              title;
    private String              description;
    private Long                userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "credit_id", nullable = false)
    private List<PaymentPeriod> paymentPeriods;

    public Credit(String title, String description, Long userId, List<PaymentPeriod> paymentPeriods) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PaymentPeriod> getPaymentPeriods() {
        return paymentPeriods;
    }

    public void setPaymentPeriods(List<PaymentPeriod> paymentPeriods) {
        this.paymentPeriods = paymentPeriods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credit credit = (Credit) o;

        if (!title.equals(credit.title)) return false;
        if (!description.equals(credit.description)) return false;
        if (!userId.equals(credit.userId)) return false;
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
