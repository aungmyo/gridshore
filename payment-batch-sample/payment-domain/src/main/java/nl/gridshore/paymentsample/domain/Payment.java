package nl.gridshore.paymentsample.domain;

import java.io.Serializable;

public class Payment implements Serializable {
    
    private Integer id;
    private Double amount;
    private String type;
    private PaymentStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.status.toString();
    }

    public void setStatus(String status) {
        this.status = PaymentStatus.valueOf(status.toUpperCase());
    }
}
