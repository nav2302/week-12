package com.greatlearning.week12assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public final class ProducerService {

	private static final String TOPIC = "adminToUserTopic";

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		
		log.info(String.format("AdminProducerService=> Producing message from Admin : %s", message));

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onFailure(Throwable ex) {
				log.info("Unable to send message=[ {} ] due to : {}", message, ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Sent message=[ {} ] with offset=[ {} ]  from Admin", message, result.getRecordMetadata().offset());
			}
		});
	}
}