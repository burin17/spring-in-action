package com.gmail.burinigor7.tacos.messaging;

import com.gmail.burinigor7.tacos.domain.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
