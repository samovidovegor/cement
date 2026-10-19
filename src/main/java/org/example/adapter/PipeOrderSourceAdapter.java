
package org.example.adapter;

import org.example.model.Order;
import org.example.parser.OrderParser;
import org.example.reader.FileOrderReader;
import org.example.source.OrderSource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class PipeOrderSourceAdapter implements OrderSource {

    private final FileOrderReader reader;
    private final OrderParser parser;

    public PipeOrderSourceAdapter(
            FileOrderReader reader,
            OrderParser parser
    ) {
        this.reader = reader;
        this.parser = parser;
    }

    @Override
    public boolean supports(Path path) {
        return path.getFileName() != null
                && path.getFileName().toString()
                .toLowerCase(Locale.ROOT)
                .endsWith(".txt");
    }

    @Override
    public List<Order> readOrders(Path path) throws IOException {
        if (!supports(path)) {
            throw new IOException(
                    "Pipe adapter supports only .txt files: " + path
            );
        }

        List<String> lines = reader.read(path);
        return parser.parse(lines, "|");
    }
}

