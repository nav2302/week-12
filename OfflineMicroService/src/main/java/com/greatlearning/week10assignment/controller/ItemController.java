package com.greatlearning.week10assignment.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.week10assignment.config.SwaggerConfig;
import com.greatlearning.week10assignment.model.Item;
import com.greatlearning.week10assignment.service.ItemServiceImpl;
import com.greatlearning.week10assignment.service.UserService;

import io.swagger.annotations.Api;

@Api(tags = { SwaggerConfig.ItemController_TAG })
@RestController
@RequestMapping("/api/items")
public class ItemController {

	@Autowired
	ItemServiceImpl itemService;

	@Autowired
	UserService userService;

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	public @NotNull ResponseEntity<Iterable<Item>> seeMenuOnline() {

		return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK) ;
	}

}
