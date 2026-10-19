
package org.example.service;

import org.example.model.Order;
import org.example.source.OrderSource;
import org.example.writer.FileOrderWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class OrderProcessor {

    private final List<OrderSource> orderSources;
    private final OrderProcessingService processingService;
    private final FileOrderWriter writer;

    public OrderProcessor(
            List<OrderSource> orderSources,
            OrderProcessingService processingService,
            FileOrderWriter writer
    ) {
        this.orderSources = List.copyOf(orderSources);
        this.processingService = processingService;
        this.writer = writer;
    }

    public void run(Path inputPath, Path outputPath) throws IOException {
        OrderSource source = orderSources.stream()
                .filter(candidate -> candidate.supports(inputPath))
                .findFirst()
                .orElseThrow(() -> new IOException(
                        "Unsupported input file format: " + inputPath
                ));

        List<Order> orders;

        try {
            orders = source.readOrders(inputPath);
        } catch (IllegalArgumentException exception) {
            throw new IOException(
                    "Input file is corrupted: " + inputPath,
                    exception
            );
        }

        Map<String, BigDecimal> totals =
                processingService.process(orders);

        writer.write(outputPath, totals);
    }
}





