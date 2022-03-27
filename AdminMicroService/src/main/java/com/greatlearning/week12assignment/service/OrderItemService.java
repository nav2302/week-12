package com.greatlearning.week12assignment.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.greatlearning.week12assignment.model.OrderItem;

@Validated
public interface OrderItemService {

    OrderItem create(@NotNull(message = "The Items for order cannot be null.") @Valid OrderItem orderItem);
}