package com.cogent.inventoryservice.service;

import com.cogent.inventoryservice.payload.Item;
import com.cogent.inventoryservice.payload.ItemEvent;
import com.cogent.inventoryservice.payload.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class OrderConsumer {
    @Autowired
    private ItemProducer itemProducer;

    @Autowired
    private InventoryService inventoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.consumer.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event received in inventory service -> %s", orderEvent.toString()));
        Item item = inventoryService.getItemByName(orderEvent.getOrder().getName());
        ItemEvent itemEvent = new ItemEvent();

        if(item.getQuantity() >= orderEvent.getOrder().getQuantity()) {
            itemEvent.setStatus("SUCCESS");
            itemEvent.setMessage("Item quantity is sufficient to meet the order.");

            // updating item quantity in database
            item.setQuantity(item.getQuantity() - orderEvent.getOrder().getQuantity());
            inventoryService.updateItem(item.getItemId(), item);

            // setting itemEvent quantity to match orderEvent quantity to send back to order-service
            item.setQuantity(orderEvent.getOrder().getQuantity());
            itemEvent.setItem(item);
        } else {
            itemEvent.setStatus("FAILED");
            itemEvent.setMessage("Item quantity is not sufficient to meet the order.");
            itemEvent.setItem(null);
        }
        itemProducer.sendMessage(itemEvent);
    }

}
