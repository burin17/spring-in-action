package com.gmail.burinigor7.tacos.kitchen.messaging.jms;

import com.gmail.burinigor7.tacos.kitchen.OrderReceiver;
import com.gmail.burinigor7.tacos.kitchen.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jmsTemplate;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Order receiveOrder() throws JMSException {
        return (Order) jmsTemplate.receiveAndConvert(
                "tacocloud.order.queue");
    }
}
