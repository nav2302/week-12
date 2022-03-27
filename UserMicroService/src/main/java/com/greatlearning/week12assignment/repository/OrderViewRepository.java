package com.greatlearning.week10assignment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.week10assignment.model.OrdersView;


@Repository
public interface OrderViewRepository extends JpaRepository<OrdersView, Long> {

	List<OrdersView> findAllByEmail(String email);

	List<OrdersView> findAllByEmailAndDateCreated(String email, LocalDate date);

	List<OrdersView> findAllByEmailAndPrice(String email, Double price);
}
