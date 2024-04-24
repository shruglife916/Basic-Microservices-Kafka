package com.cogent.orderservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.awt.event.ItemEvent;

@Service
public class ItemConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.consumer.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ItemEvent itemEvent) {
        LOGGER.info(String.format("Item event received in order service -> %s", itemEvent.toString()));
    }
}
