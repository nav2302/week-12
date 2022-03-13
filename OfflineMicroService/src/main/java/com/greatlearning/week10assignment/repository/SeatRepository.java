package com.greatlearning.week10assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.week10assignment.model.Reservation;

@Repository
public interface SeatRepository extends JpaRepository<Reservation, Long>{

	Reservation findByNumber(long seatNumber);

}

