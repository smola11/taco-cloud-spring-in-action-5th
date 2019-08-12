package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.kitchen.OrderReceiver;

@Profile("rabbitmq-template")
@Component("templateOrderReceiver")
public class RabbitOrderReceiver implements OrderReceiver {

    private RabbitTemplate rabbitTemplate;

    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Order receiveOrder() {
        return (Order) rabbitTemplate.receiveAndConvert("tacocloud.orders", new ParameterizedTypeReference<Order>() {
        });
    }
}

