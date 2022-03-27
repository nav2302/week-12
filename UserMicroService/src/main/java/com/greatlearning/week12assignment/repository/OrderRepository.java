package com.greatlearning.week10assignment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.week10assignment.model.Order;
import com.greatlearning.week10assignment.model.OrderItemPK;
import com.greatlearning.week10assignment.model.OrdersView;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderItemPK> {

	List<Order> findAllByDateCreated(LocalDate date);
	List<Order> findByDateCreatedGreaterThanAndDateCreatedLessThan(LocalDate start, LocalDate end);
}