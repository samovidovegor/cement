

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
    public boolean supports(Path path) {
        if (path.getFileName() == null) {
            return false;
        }

        String fileName = path.getFileName().toString();

        return !fileName.isBlank() && !fileName.contains(".");
    }

    @Override
    public List<Order> readOrders(Path path) throws IOException {
        if (!supports(path)) {
            throw new IOException(
                    "Hash adapter supports only files without extension: "
                            + path
            );
        }

        List<String> lines = reader.read(path);
        return parser.parse(lines, "#");
    }
}
