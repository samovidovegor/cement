package service;

import java.math.BigDecimal;
// рассчитывает скидку по позиции заказа.
public class DiscountCalculator {
    private BigDecimal startDiscount = new BigDecimal("0.50");
    private BigDecimal discountStep = new BigDecimal("0.05");

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
