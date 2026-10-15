package org.example.service;

import org.example.model.Order;
import org.example.parser.OrderParser;
import org.example.reader.FileOrderReader;
import org.example.writer.FileOrderWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;

// пока выступает координатором всего процесса.

public class OrderProcessor {

    private final FileOrderReader reader;
    private final OrderParser parser;
    private final OrderProcessingService processingService;
    private final FileOrderWriter writer;

    public OrderProcessor(
            FileOrderReader reader,
            OrderParser parser,
            OrderProcessingService processingService,
            FileOrderWriter writer
    ) {
        this.reader = reader;
        this.parser = parser;
        this.processingService = processingService;
        this.writer = writer;
    }

    public void run(Path inputPath, Path outputPath) throws IOException {
        List<String> lines = reader.read(inputPath);

        List<Order> orders = parser.parse(lines);

        Map<String, BigDecimal> totals =
                processingService.process(orders);

        writer.write(outputPath, totals);
    }
}




