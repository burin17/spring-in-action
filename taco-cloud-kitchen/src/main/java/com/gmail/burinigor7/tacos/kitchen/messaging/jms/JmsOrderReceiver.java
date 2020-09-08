package com.gmail.burinigor7.tacos.kitchen;

import com.gmail.burinigor7.tacos.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;

public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jmsTemplate;
    private MessageConverter converter;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jmsTemplate,
                            MessageConverter converter) {
        this.jmsTemplate = jmsTemplate;
        this.converter = converter;
    }

    @Override
    public Order receiveOrder() throws JMSException {
        Message message = jmsTemplate.receive("tacocloud.order.queue");
        return (Order) converter.fromMessage(message);
    }
}
