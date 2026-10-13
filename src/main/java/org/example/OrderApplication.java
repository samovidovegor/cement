package org.example;

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

        OrderProcessingService processingService =
                new OrderProcessingService();

        Map<String, BigDecimal> totals =
                processingService.process(orders);

        FileOrderWriter writer = new FileOrderWriter();
        writer.write(outputPath, totals);
    }
}




