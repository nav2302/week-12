package com.greatlearning.week10assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
@Entity
public class FeedBackForm{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "feed_back")
	private String feedBack;
	
	@Column(name = "star_rating")
	private int starRating;
	
}