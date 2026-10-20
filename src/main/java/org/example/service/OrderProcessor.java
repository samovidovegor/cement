
package org.example.service;

import org.example.exception.UnsupportedFileFormatException;
import org.example.model.Order;
import org.example.source.OrderSource;
import org.example.writer.FileOrderWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;

public class OrderProcessor {

    private final Map<String, OrderSource> sourcesByExtension;
    private final OrderProcessingService processingService;
    private final FileOrderWriter writer;

    public OrderProcessor(
            Map<String, OrderSource> sourcesByExtension,
            OrderProcessingService processingService,
            FileOrderWriter writer
    ) {
        this.sourcesByExtension = Map.copyOf(sourcesByExtension);
        this.processingService = processingService;
        this.writer = writer;
    }

    public void run(Path inputPath, Path outputPath) throws IOException {
        String extension = getExtension(inputPath);

        OrderSource source = sourcesByExtension.get(extension);

        if (source == null) {
            throw new UnsupportedFileFormatException(
                    "Unsupported input file format: " + inputPath
            );
        }

        var orders = source.readOrders(inputPath);

        Map<String, BigDecimal> totals =
                processingService.process(orders);

        writer.write(outputPath, totals);
    }

    private String getExtension(Path path) {
        Path fileNamePath = path.getFileName();

        if (fileNamePath == null) {
            return "";
        }

        String fileName = fileNamePath.toString();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex <= 0) {
            return "";
        }

        return fileName.substring(dotIndex).toLowerCase(Locale.ROOT);
    }
}
