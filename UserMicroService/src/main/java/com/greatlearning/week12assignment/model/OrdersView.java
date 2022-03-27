package com.greatlearning.week10assignment.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.NoArgsConstructor;

@Entity
@Immutable
@Table(name = "`ALL_ORDERS_FOR_CURRENT_USER`")
@NoArgsConstructor
public class OrdersView {
	
	@Id
    @Column(name = "ID")
    private Long id; // The row number!

	@Column
	private Integer quantity;
	
	@Column
	private LocalDate dateCreated;

	@Column
	private String name;

	@Column
	private Double price;
	
	@Column
	private String email;

	public Long getId() {
		return id;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public String getEmail() {
		return email;
	}

}
