package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProcessingService {

    public Map<String, BigDecimal> process(List<Order> orders) {

        DiscountCalculator discountCalculator = new DiscountCalculator(
                new BigDecimal("0.50"),
                new BigDecimal("0.05")
        );

        OrderCostCalculator costCalculator = new OrderCostCalculator();
        OrderSorter sorter = new OrderSorter();
        OrderAggregator aggregator = new OrderAggregator();

        List<Order> sortedOrders = sorter.sort(orders);

        BigDecimal pricePerKg = new BigDecimal("10");

        Map<String, BigDecimal> totals = new HashMap<>();

        for (int i = 0; i < sortedOrders.size(); i++) {

            BigDecimal discount = discountCalculator.calculate(i);

            BigDecimal cost = costCalculator.calculate(
                    sortedOrders.get(i),
                    pricePerKg,
                    discount
            );

            aggregator.aggregate(
                    totals,
                    sortedOrders.get(i),
                    cost
            );
        }

        return totals;
    }
}
