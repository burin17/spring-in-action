package com.gmail.burinigor7.tacos.kitchen;


import com.gmail.burinigor7.tacos.kitchen.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KitchenUI {
    public void displayOrder(Order order) {
        log.info("RECEIVED ORDER : " + order);
    }
}
