package com.greatlearning.week12assignment.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.NoArgsConstructor;

@Entity
@Immutable // Making it Immutable so for READ ONLY purpose
@Table(name = "`MONTHLY_SALE_LAST_YEAR`")
@NoArgsConstructor
public class MonthlySaleLastYear {

	@Id
	@Column(name = "ID")
	private Long id; // The row number!

	@Column
	private LocalDate dateCreated;
	
	@Column
	Double sales;

}
