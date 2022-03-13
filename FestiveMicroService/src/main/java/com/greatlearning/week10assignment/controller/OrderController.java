package com.greatlearning.week10assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.greatlearning.week10assignment.config.SwaggerConfig;
import com.greatlearning.week10assignment.exception.ItemNotFoundException;
import com.greatlearning.week10assignment.model.Item;
import com.greatlearning.week10assignment.model.Order;
import com.greatlearning.week10assignment.model.OrderBillWrapper;
import com.greatlearning.week10assignment.model.OrderItem;
import com.greatlearning.week10assignment.model.OrderItemDto;
import com.greatlearning.week10assignment.model.User;
import com.greatlearning.week10assignment.response.ItemResponse;
import com.greatlearning.week10assignment.service.ItemService;
import com.greatlearning.week10assignment.service.OrderItemService;
import com.greatlearning.week10assignment.service.OrderService;
import com.greatlearning.week10assignment.service.UserService;

import io.swagger.annotations.Api;

@Api(tags = { SwaggerConfig.OrderController_TAG })
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@PostMapping
	@Transactional
	public ResponseEntity<OrderBillWrapper> generateBill(@RequestBody OrderForm form) {
		List<OrderItemDto> formDtos = form.getItemOrders();

		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());

		validateProductsExistence(formDtos);
		Order order = new Order();
		user.addOrder(order);
		order = this.orderService.create(order);

		List<OrderItem> orderItems = new ArrayList<>();
		for (OrderItemDto dto : formDtos) {
			orderItems.add(orderItemService
					.create(new OrderItem(order, itemService.findItemById(dto.getItem().getId()), dto.getQuantity())));
		}
		order.setOrderItems(orderItems);
		this.orderService.update(order);

		String uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/orders/{id}")
				.buildAndExpand(order.getId()).toString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", uri);
		
		
		List<ItemResponse> items = new ArrayList<>();
		order.getOrderItems().stream().forEach(orderItem -> {
			Item item = orderItem.getItem();
			items.add(ItemResponse.builder().name(item.getName()).price(item.getPrice()).quantity(orderItem.getQuantity()).build());
		});
		
		Double totalBill = order.getTotalOrderPrice();
		if(form.getDiscountcode() != null && form.getDiscountcode().equalsIgnoreCase("FESTIVECODE"))
			totalBill = (double) totalBill/2;
		return new ResponseEntity<>(OrderBillWrapper.builder().bill(totalBill).items(items).build(), headers, HttpStatus.CREATED);
	}

	public static class OrderForm {

		private List<OrderItemDto> itemOrders;
		private String discountcode;

		public String getDiscountcode() {
			return discountcode;
		}

		public void setDiscountcode(String discountcode) {
			this.discountcode = discountcode;
		}

		public List<OrderItemDto> getItemOrders() {
			return itemOrders;
		}

		public void setItemOrders(List<OrderItemDto> itemOrders) {
			this.itemOrders = itemOrders;
		}
	}

	private void validateProductsExistence(List<OrderItemDto> orderProducts) {
		List<OrderItemDto> list = orderProducts.stream()
				.filter(op -> Objects.isNull(itemService.findItemById(op.getItem().getId())))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(list)) {
			new ItemNotFoundException("Item not found");
		}
	}
}
