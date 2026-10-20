package org.example.service;

import org.example.model.Order;

import java.math.BigDecimal;
import java.util.Map;

public class OrderAggregator {

    public void aggregate(
            Map<String, BigDecimal> totals,
            Order order,
            BigDecimal cost
    ) {
        String companyName = order.getCompanyName();

        if (totals.containsKey(companyName)) {
            BigDecimal currentTotal = totals.get(companyName);
            totals.put(companyName, currentTotal.add(cost));
        } else {
            totals.put(companyName, cost);
        }
    }
}
