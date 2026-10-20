package org.example.service;

import org.example.model.Order;

import java.math.BigDecimal;
// считает стоимость одного заказа с учётом скидки.
public class OrderCostCalculator {
    public BigDecimal calculate(Order order, BigDecimal pricePerKg, BigDecimal discount) {
        BigDecimal amount = order.getAmountKg();

        return amount
                .multiply(pricePerKg)
                .multiply(BigDecimal.ONE.subtract(discount));
    }
}
