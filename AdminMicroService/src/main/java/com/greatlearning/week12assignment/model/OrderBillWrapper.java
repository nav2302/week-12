package com.greatlearning.week12assignment.model;

import java.io.Serializable;
import java.util.List;

import com.greatlearning.week12assignment.response.ItemResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBillWrapper implements Serializable{
	
	/**
	 * @author nav
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ItemResponse> items;
	private Double bill;

}
