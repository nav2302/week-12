package com.greatlearning.week12assignment.service;

import java.time.LocalDate;
import java.util.List;

import com.greatlearning.week12assignment.model.Order;

public interface OrderService {
	public Iterable<Order> getAllOrders();
	
    public Order create(Order order);

    public void update(Order order);

	public List<Order> findAllByDateCreated(LocalDate date);

	public List<Order> findOrdersForThisMonth(LocalDate start, LocalDate end);
}
