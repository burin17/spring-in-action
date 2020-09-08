package com.gmail.burinigor7.tacos.kitchen.messaging.jms;

import com.gmail.burinigor7.tacos.kitchen.domain.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;
import java.util.Map;

//@Profile({"jms-template", "jms-listener"})
@Configuration
public class MessagingConfig {

    @Bean
    public MappingJackson2MessageConverter converter() {
        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", Order.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }
}
