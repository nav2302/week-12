package com.greatlearning.week12assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.week12assignment.model.MonthlySaleLastYear;

@Repository
public interface MonthlySalesLastYearRepository extends JpaRepository<MonthlySaleLastYear, Long>{

}
