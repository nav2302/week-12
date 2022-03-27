package com.greatlearning.week12assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.NoArgsConstructor;

@Entity
@Immutable // Making it Immutable so for READ ONLY purpose
@Table(name = "`SALES_BY_CITIES`")
@NoArgsConstructor
public class SalesByCitiesView {

	@Id
	@Column(name = "ID")
	private Long id; // The row number!

	@Column
	private String city;

	private Double sales;

	public Long getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public Double getSales() {
		return sales;
	}

}
