package org.example.service;

import java.math.BigDecimal;

// рассчитывает скидку по позиции заказа.
public class DiscountCalculator {

    private final BigDecimal startDiscount;
    private final BigDecimal discountStep;

    public DiscountCalculator(
            BigDecimal startDiscount,
            BigDecimal discountStep
    ) {
        this.startDiscount = startDiscount;
        this.discountStep = discountStep;
    }

    public BigDecimal calculate(int orderIndex) {
        BigDecimal discount = startDiscount.subtract(
                discountStep.multiply(BigDecimal.valueOf(orderIndex))
        );

        if (discount.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }

        return discount;
    }
}
