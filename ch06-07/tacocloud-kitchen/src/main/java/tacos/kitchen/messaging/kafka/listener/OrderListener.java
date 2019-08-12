package tacos.kitchen.messaging.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.kitchen.KitchenUI;

@Profile("kafka-listener")
@Component
@Slf4j
public class OrderListener {

    private KitchenUI kitchenUI;

    public OrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(Order order, ConsumerRecord<String, Order> record) {
        log.info("Received from partition {} with timestamp {}", record.partition(), record.timestamp());
        kitchenUI.displayOrder(order);
    }

// Alternate implementation
//
//  @KafkaListener(topics="tacocloud.orders.topic")
//  public void handle(Order order, Message<Order> message) {
//    MessageHeaders headers = message.getHeaders();
//    log.info("Received from partition {} with timestamp {}",
//        headers.get(KafkaHeaders.RECEIVED_PARTITION_ID),
//        headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
//    ui.displayOrder(order);
//  }
}
