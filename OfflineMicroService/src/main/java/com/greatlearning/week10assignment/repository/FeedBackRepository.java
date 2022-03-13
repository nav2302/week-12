package com.greatlearning.week10assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.week10assignment.model.FeedBackForm;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBackForm, Long>{

}

