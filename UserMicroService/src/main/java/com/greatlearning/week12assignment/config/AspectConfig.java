package com.greatlearning.week10assignment.config;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.greatlearning.week10assignment.controller.OrderController.OrderForm;
import com.greatlearning.week10assignment.model.AuditLog;
import com.greatlearning.week10assignment.repository.AuditRepository;

@Configuration
@Aspect
public class AspectConfig {
	
	@Autowired
	AuditRepository auditRepository;
	
	@AfterReturning("execution(public * com.greatlearning.week10assignment.controller.OrderController.generateBill(..) )")
	public void logBeforeGenrateBill(JoinPoint joinPoint) {
		
		OrderForm orderForm = (OrderForm) joinPoint.getArgs()[0];
		auditRepository.save(AuditLog.builder().createDate(new Date()).description("Bill Generated " + orderForm.getItemOrders()).build());
	}

}
