package com.greatlearning.week10assignment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.week10assignment.model.OrderItem;
import com.greatlearning.week10assignment.repository.OrderItemRepository;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
    private OrderItemRepository orderProductRepository;

    @Override
    public OrderItem create(OrderItem orderItem) {
        return this.orderProductRepository.save(orderItem);
    }
}
