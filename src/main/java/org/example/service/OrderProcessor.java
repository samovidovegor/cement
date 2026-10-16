package org.example.service;

import org.example.model.Order;
import org.example.parser.OrderParser;
import org.example.reader.FileOrderReader;
import org.example.source.OrderSource;
import org.example.writer.FileOrderWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;

// пока выступает координатором всего процесса.

public class OrderProcessor {

    private final OrderSource orderSource;
    private final OrderProcessingService processingService;
    private final FileOrderWriter writer;

    public OrderProcessor(
            OrderSource orderSource,
            OrderProcessingService processingService,
            FileOrderWriter writer
    ) {
        this.orderSource = orderSource;
        this.processingService = processingService;
        this.writer = writer;
    }

    public void run(Path inputPath, Path outputPath) throws IOException {
        List<Order> orders = orderSource.readOrders(inputPath);

        Map<String, BigDecimal> totals =
                processingService.process(orders);

        writer.write(outputPath, totals);
    }
}




