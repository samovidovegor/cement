package org.example.service;

import org.example.model.Order;

import java.util.Comparator;
import java.util.List;
// сортирует заказы по времени.
public class OrderSorter {
    public List<Order> sort(List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getSubmissionTime));
        return orders;
    }
}

