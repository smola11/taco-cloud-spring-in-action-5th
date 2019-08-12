package tacos.kitchen.messaging.rabbit.listener;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tacos.kitchen.KitchenUI;

@Profile("rabbitmq-listener")
@Component
public class OrderListener {

    private KitchenUI kitchenUI;

    public void receiveOrder(){
        
    }
}
