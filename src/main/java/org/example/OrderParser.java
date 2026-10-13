package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderParser {

    public List<Order> parse(List<String> lines) {
        List<Order> orders = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("\\|");

            LocalDateTime submissionTime =
                    LocalDateTime.parse(parts[0]);

            String companyName = parts[1];

            BigDecimal amountKg = new BigDecimal(parts[2]);

            Order order = new Order(
                    submissionTime,
                    companyName,
                    amountKg
            );

            orders.add(order);
        }

        return orders;
    }
}