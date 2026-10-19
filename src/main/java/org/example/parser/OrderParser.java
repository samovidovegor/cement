

package org.example.parser;

import org.example.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class OrderParser {

    public List<Order> parse(List<String> lines) {
        return parse(lines, "|");
    }

    public List<Order> parse(List<String> lines, String delimiter) {
        List<Order> orders = new ArrayList<>();
        String regex = Pattern.quote(delimiter);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line == null || line.isBlank()) {
                continue;
            }

            try {
                String[] parts = line.split(regex, -1);

                if (parts.length != 3
                        || parts[0].isBlank()
                        || parts[1].isBlank()
                        || parts[2].isBlank()) {
                    throw new IllegalArgumentException(
                            "Expected timestamp, company and weight"
                    );
                }

                LocalDateTime submissionTime =
                        LocalDateTime.parse(parts[0].trim());

                String companyName = parts[1].trim();

                BigDecimal amountKg =
                        new BigDecimal(parts[2].trim());

                if (amountKg.signum() < 0) {
                    throw new IllegalArgumentException(
                            "Order weight cannot be negative"
                    );
                }

                orders.add(new Order(
                        submissionTime,
                        companyName,
                        amountKg
                ));

            } catch (RuntimeException exception) {
                throw new IllegalArgumentException(
                        "Invalid order at line " + (i + 1)
                                + ": " + line,
                        exception
                );
            }
        }

        return orders;
    }
}

