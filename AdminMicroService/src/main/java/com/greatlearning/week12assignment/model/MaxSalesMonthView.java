package com.greatlearning.week12assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.NoArgsConstructor;

@Entity
@Immutable // Making it Immutable so for READ ONLY purpose
@Table(name = "`MAX_SALES_IN_A_MONTH`")
@NoArgsConstructor
public class MaxSalesMonthView {

	@Id
	@Column(name = "ID")
	private Long id; // The row number!

	@Column
	private Double sales;

}
