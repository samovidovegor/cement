package writer;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileOrderWriter {
    public void write(
            Path path,
            Map<String, BigDecimal> totals
    ) throws IOException {

        List<String> lines = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : totals.entrySet()) {
            String line = entry.getKey() + " - " + entry.getValue();
            lines.add(line);
        }

        Files.write(path, lines);

    }
}
