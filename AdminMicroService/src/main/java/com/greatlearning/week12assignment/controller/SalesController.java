package com.greatlearning.week12assignment.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.week12assignment.config.SwaggerConfig;
import com.greatlearning.week12assignment.model.Item;
import com.greatlearning.week12assignment.model.MaxSalesMonthView;
import com.greatlearning.week12assignment.model.MonthlySaleLastYear;
import com.greatlearning.week12assignment.model.Order;
import com.greatlearning.week12assignment.model.OrderBillWrapper;
import com.greatlearning.week12assignment.model.SalesByCitiesView;
import com.greatlearning.week12assignment.repository.MaxSalesMonthRepository;
import com.greatlearning.week12assignment.repository.MonthlySalesLastYearRepository;
import com.greatlearning.week12assignment.repository.SalesByCitiesRepository;
import com.greatlearning.week12assignment.response.ItemResponse;
import com.greatlearning.week12assignment.service.OrderService;

import io.swagger.annotations.Api;


@Api(tags = { SwaggerConfig.SalesController_TAG })
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")// Authorizing only Admin
public class SalesController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	SalesByCitiesRepository salesByCitiesRepository;
	
	@Autowired
	MaxSalesMonthRepository maxSalesMonthRepository;
	
	@Autowired
	MonthlySalesLastYearRepository monthlySalesLastYearRepository;
	
	/*
	 * START
	 * Code for WEEK-11 assignment 
	 * */
	
	
	
	@GetMapping(value = "/sales/citywise")
	public ResponseEntity<Iterable<SalesByCitiesView>> getSalesByCities() {
		
		return new ResponseEntity<>(salesByCitiesRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/sales/maxSalesInMonth")
	public ResponseEntity<Iterable<MaxSalesMonthView>> getSalesByMonth() {
		
		return new ResponseEntity<>(maxSalesMonthRepository.findAll(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/sales/monthlySalesLastYear")
	public ResponseEntity<Iterable<MonthlySaleLastYear>> getSalesMonthlyLastYear() {
		
		return new ResponseEntity<>(monthlySalesLastYearRepository.findAll(), HttpStatus.OK);
	}
	
	
	/*
	 * END
	 * Code for WEEK-11 assignment 
	 * */
	@GetMapping(value = "/bills")
	public ResponseEntity<OrderBillWrapper> getAllOrdersForToday() {

		List<Order> orderList = Optional.ofNullable(orderService.findAllByDateCreated(LocalDate.now())).orElse(null);
		Double totalBill = orderList.stream().mapToDouble(Order::getTotalOrderPrice).sum();

		List<ItemResponse> items = new ArrayList<>();
		orderList.stream().forEach(order -> order.getOrderItems().forEach(orderItem -> {
			Item item = orderItem.getItem();
			items.add(ItemResponse.builder().name(item.getName()).price(item.getPrice())
					.quantity(orderItem.getQuantity()).build());
		}));
		return new ResponseEntity<>(OrderBillWrapper.builder().bill(totalBill).items(items).build(), HttpStatus.OK);
	}

	@GetMapping(value = "/sales")
	public ResponseEntity<String> getSalesForThisMonth() {
		LocalDate start = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000)).withDayOfMonth(1);

		LocalDate end = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000)).plusMonths(1)
				.withDayOfMonth(1).minusDays(1);
		List<Order> orderList = Optional.ofNullable(orderService.findOrdersForThisMonth(start, end)).orElse(null);

		Double totalSales = orderList.stream().mapToDouble(Order::getTotalOrderPrice).sum();
		return new ResponseEntity<>(new String("Total sales for this month " + totalSales), HttpStatus.OK);
	}
	
}
