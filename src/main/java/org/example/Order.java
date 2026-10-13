package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
// хранит данные заказа
public class Order {
    private LocalDateTime submissionTime;
    private String companyName;
    private BigDecimal amountKg;

    public Order(LocalDateTime submissionTime, String companyName, BigDecimal amountKg) {
        this.submissionTime = submissionTime;
        this.companyName = companyName;
        this.amountKg = amountKg;
    }

    public LocalDateTime getSubmissionTime() {

        return submissionTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BigDecimal getAmountKg() {
        return amountKg;
    }
}
