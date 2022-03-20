package com.greatlearning.week10assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.week10assignment.model.MaxSalesMonthView;

@Repository
public interface MaxSalesMonthRepository extends JpaRepository<MaxSalesMonthView, Long> {
}