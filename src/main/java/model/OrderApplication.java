package model;

import reader.FileOrderReader;
import service.DiscountCalculator;
import service.OrderAggregator;
import service.OrderCostCalculator;
import service.OrderSorter;
import writer.FileOrderWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.net.URISyntaxException;

// пока выступает координатором всего процесса.
public class OrderApplication {

    public void run() throws IOException, URISyntaxException {
        Path path = Paths.get(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("discount_day.txt")
                ).toURI()
        );
        FileOrderReader reader = new FileOrderReader();
        List<Order> orders = reader.read(path);

        DiscountCalculator discountCalculator = new DiscountCalculator();
        OrderCostCalculator costCalculator = new OrderCostCalculator();

        OrderSorter sorter = new OrderSorter();
        List<Order> sortedOrders = sorter.sort(orders);

        BigDecimal pricePerKg = new BigDecimal("10");


        Map<String, BigDecimal> totals = new HashMap<>();

        OrderAggregator aggregator = new OrderAggregator();

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

        FileOrderWriter writer = new FileOrderWriter();

        Path outputPath = Path.of("result.txt");

        writer.write(outputPath, totals);
    }


}


