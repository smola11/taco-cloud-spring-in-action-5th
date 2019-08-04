package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.Order;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    private JmsTemplate jmsTemplate;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        jmsTemplate.convertAndSend("tacocloud.order.queue", order, this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
//    OLD VERSION WITHOUT LAMBDAS
//    @Override
//    public void sendOrder(Order order) {
//
//        jmsTemplate.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(order);
//            }
//        });
//    }
}
