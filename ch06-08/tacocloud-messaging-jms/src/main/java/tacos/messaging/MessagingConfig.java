package tacos.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import tacos.Order;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId"); // enables the receiver to know what type to convert an incoming message to;

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", Order.class); //
        messageConverter.setTypeIdMappings(typeIdMappings);
        // In the message’s _typeId property, the value 'order' will be sent.
        // At the receiving application, a similar message converter will have been configured, mapping order to its own understanding of what an order is.

        return messageConverter;
    }
}
