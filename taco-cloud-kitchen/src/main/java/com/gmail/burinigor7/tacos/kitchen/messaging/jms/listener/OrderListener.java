package com.gmail.burinigor7.tacos.kitchen.messaging.jms.listener;

import com.gmail.burinigor7.tacos.kitchen.KitchenUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {
    private KitchenUI kitchenUI;

    @Autowired
    public OrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }

//    @JmsListener(destination = "tacocloud.order.queue")
//    public void receiveOrder(Order order) {
//        kitchenUI.displayOrder(order);
//    }
}
