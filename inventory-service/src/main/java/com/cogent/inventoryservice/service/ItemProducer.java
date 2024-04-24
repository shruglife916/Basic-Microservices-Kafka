package com.cogent.inventoryservice.service;

import com.cogent.inventoryservice.payload.ItemEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ItemProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemProducer.class);

    @Autowired
    private NewTopic newTopic;

    @Autowired
    private KafkaTemplate<String, ItemEvent> kafkaTemplate;

    public void sendMessage(ItemEvent itemEvent) {
        LOGGER.info(String.format("Item event => %s", itemEvent.toString()));

        // create message
        Message<ItemEvent> message = MessageBuilder
                .withPayload(itemEvent)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
