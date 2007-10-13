package nl.gridshore.paymentsample.domain;

import java.io.Serializable;

public class Payment extends BaseDomain implements Serializable {
    
    private Double amount;
    private PaymentType type;
    private PaymentStatus status;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public PaymentStatus getStatus() {
        return this.status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return !(getId() != null ? !getId().equals(payment.getId()) : payment.getId() != null);

    }

    public int hashCode() {
        return (getId() != null ? getId().hashCode() : 0);
    }
}
