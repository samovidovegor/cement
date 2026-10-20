
package org.example.source;

import org.example.model.Order;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface OrderSource {

    String getSupportedExtension();

    List<Order> readOrders(Path path) throws IOException;
}
