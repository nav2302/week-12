package com.greatlearning.week10assignment.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.week10assignment.config.SwaggerConfig;
import com.greatlearning.week10assignment.model.Reservation;
import com.greatlearning.week10assignment.model.User;
import com.greatlearning.week10assignment.repository.SeatRepository;
import com.greatlearning.week10assignment.service.UserService;

import io.swagger.annotations.Api;

@Api(tags = { SwaggerConfig.SeatController_TAG })
@RestController
@RequestMapping("/api/bookseat")
public class SeatController {

	@Autowired
	UserService userService;

	@Autowired
	SeatRepository seatRepository;

	@GetMapping
	public ResponseEntity<String> bookSeat(@RequestParam(name = "number", required = true) long seatNumber,
			@RequestParam(name = "from", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());

		long days = TimeUnit.MILLISECONDS.toDays(new Date().getTime() - fromDate.getTime());
		if (days < 2) {
			return new ResponseEntity<>("Seat Cannot be booked now. Please try before 2 days!",
					HttpStatus.EXPECTATION_FAILED);
		}

		Reservation seat = seatRepository.findByNumber(seatNumber);
		if (seat == null) {
			try {
				seatRepository.save(Reservation.builder().number(seatNumber).date(fromDate).user(user).build());
				return new ResponseEntity<>("Seat Number: " + seatNumber + " reserved!", HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<>("This user has already reserved a seat. Try with different user",
						HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<>("Selected Seat Already Reserved!", HttpStatus.NOT_FOUND);

	}

}
