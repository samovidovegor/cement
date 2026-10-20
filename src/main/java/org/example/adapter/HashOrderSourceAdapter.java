
package org.example.adapter;

import org.example.model.Order;
import org.example.parser.OrderParser;
import org.example.reader.FileOrderReader;
import org.example.source.OrderSource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class HashOrderSourceAdapter implements OrderSource {

    private final FileOrderReader reader;
    private final OrderParser parser;

    public HashOrderSourceAdapter(
            FileOrderReader reader,
            OrderParser parser
    ) {
        this.reader = reader;
        this.parser = parser;
    }

    @Override
    public String getSupportedExtension() {
        return "";
    }

    @Override
    public List<Order> readOrders(Path path) throws IOException {
        List<String> lines = reader.read(path);
        return parser.parse(lines, "#");
    }
}
