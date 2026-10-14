package org.example.service;

import org.example.model.Order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProcessingService {

    private final DiscountCalculator discountCalculator;
    private final OrderCostCalculator costCalculator;
    private final OrderSorter sorter;
    private final OrderAggregator aggregator;
    private final BigDecimal pricePerKg;

    public OrderProcessingService(
            DiscountCalculator discountCalculator,
            OrderCostCalculator costCalculator,
            OrderSorter sorter,
            OrderAggregator aggregator,
            BigDecimal pricePerKg
    ) {
        this.discountCalculator = discountCalculator;
        this.costCalculator = costCalculator;
        this.sorter = sorter;
        this.aggregator = aggregator;
        this.pricePerKg = pricePerKg;
    }

    public Map<String, BigDecimal> process(List<Order> orders) {

        List<Order> sortedOrders = sorter.sort(orders);

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