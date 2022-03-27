package com.greatlearning.week12assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.week12assignment.service.ProducerService;

@RestController
@RequestMapping("/api/kafka")
@PreAuthorize("hasRole('ADMIN')")// Authorizing only Admin
public class ChatController {
	
	@Autowired
	ProducerService producerService;
	
	@PostMapping(value = "/send")
    public void sendMessageToKafkaTopic(@RequestParam String message) {
        producerService.sendMessage(message);
    }

}
