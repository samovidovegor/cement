
package org.example;

import org.example.adapter.HashOrderSourceAdapter;
import org.example.adapter.PipeOrderSourceAdapter;
import org.example.console.ConsoleInteraction;
import org.example.parser.OrderParser;
import org.example.reader.FileOrderReader;
import org.example.service.DiscountCalculator;
import org.example.service.OrderAggregator;
import org.example.service.OrderCostCalculator;
import org.example.service.OrderProcessingService;
import org.example.service.OrderProcessor;
import org.example.service.OrderSorter;
import org.example.source.OrderSource;
import org.example.writer.FileOrderWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // Инициализация зависимостей
        ConsoleInteraction console = new ConsoleInteraction();

        FileOrderReader reader = new FileOrderReader();
        OrderParser parser = new OrderParser();

        OrderSource pipeSource =
                new PipeOrderSourceAdapter(reader, parser);

        OrderSource hashSource =
                new HashOrderSourceAdapter(reader, parser);

        DiscountCalculator discountCalculator =
                new DiscountCalculator(
                        new BigDecimal("0.50"),
                        new BigDecimal("0.05")
                );

        OrderCostCalculator costCalculator =
                new OrderCostCalculator();

        OrderSorter sorter = new OrderSorter();
        OrderAggregator aggregator = new OrderAggregator();
        FileOrderWriter writer = new FileOrderWriter();

        BigDecimal pricePerKg = new BigDecimal("10");

        OrderProcessingService processingService =
                new OrderProcessingService(
                        discountCalculator,
                        costCalculator,
                        sorter,
                        aggregator,
                        pricePerKg
                );

        OrderProcessor application = new OrderProcessor(
                List.of(pipeSource, hashSource),
                processingService,
                writer
        );

        // Выполнение программы
        Path inputPath = console.readInputPath();
        Path outputPath = console.readOutputPath();

        application.run(inputPath, outputPath);
    }
}
