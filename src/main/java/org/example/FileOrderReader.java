package org.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// читает TXT и превращает строки в Order
public class FileOrderReader {

    public List<String> read(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
