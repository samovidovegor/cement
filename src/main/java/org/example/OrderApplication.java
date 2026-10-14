package org.example;

import org.example.model.Order;
import org.example.parser.OrderParser;
import org.example.reader.FileOrderReader;
import org.example.service.*;
import org.example.writer.FileOrderWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;

// пока выступает координатором всего процесса.

public class OrderApplication {

    public void run(Path inputPath, Path outputPath) throws IOException {

        FileOrderReader reader = new FileOrderReader();
        List<String> lines = reader.read(inputPath);

        OrderParser parser = new OrderParser();
        List<Order> orders = parser.parse(lines);

        DiscountCalculator discountCalculator = new DiscountCalculator(
                new BigDecimal("0.50"),
                new BigDecimal("0.05")
        );

        OrderCostCalculator costCalculator =
                new OrderCostCalculator();

        OrderSorter sorter = new OrderSorter();

        OrderAggregator aggregator = new OrderAggregator();

        BigDecimal pricePerKg = new BigDecimal("10");

        OrderProcessingService processingService =
                new OrderProcessingService(
                        discountCalculator,
                        costCalculator,
                        sorter,
                        aggregator,
                        pricePerKg
                );

        Map<String, BigDecimal> totals =
                processingService.process(orders);

        FileOrderWriter writer = new FileOrderWriter();
        writer.write(outputPath, totals);
    }
}




