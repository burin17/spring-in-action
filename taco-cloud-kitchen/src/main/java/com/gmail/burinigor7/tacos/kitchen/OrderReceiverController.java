package com.gmail.burinigor7.tacos.kitchen;

import com.gmail.burinigor7.tacos.kitchen.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.JMSException;
import javax.jms.Message;

@Controller
@RequestMapping("/orders")
@Slf4j
@CrossOrigin("*")
public class OrderReceiverController {
    private OrderReceiver orderReceiver;

    @Autowired
    public OrderReceiverController(OrderReceiver orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    @GetMapping("/receive")
    public String receiveOrder(Model model) throws JMSException {
        Order order = orderReceiver.receiveOrder();
        if(order != null) {
            model.addAttribute("order", order);
            return "receivedOrder";
        }
        return "noOrder";
    }
}
