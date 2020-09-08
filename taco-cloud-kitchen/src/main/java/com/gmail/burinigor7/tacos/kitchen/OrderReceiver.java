package com.gmail.burinigor7.tacos.kitchen;

import com.gmail.burinigor7.tacos.kitchen.domain.Order;

import javax.jms.JMSException;

public interface OrderReceiver {
    Order receiveOrder() throws JMSException;
}
