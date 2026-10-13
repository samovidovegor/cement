package reader;

import model.Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// читает TXT и превращает строки в Order
public class FileOrderReader {

    public List<Order> read(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);

        List<Order> orders = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            LocalDateTime submissionTime = LocalDateTime.parse(parts[0]);
            String companyName = parts[1];
            BigDecimal amountKg = new BigDecimal(parts[2]);

            Order order = new Order(submissionTime, companyName, amountKg);
            orders.add(order);

        }

        return orders;
    }
}
