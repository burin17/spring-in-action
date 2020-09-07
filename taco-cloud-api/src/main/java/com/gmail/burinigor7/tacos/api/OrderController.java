package com.gmail.burinigor7.tacos.api;

import com.gmail.burinigor7.tacos.data.OrderRepository;
import com.gmail.burinigor7.tacos.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@RequestBody Order patch,
                            @PathVariable("orderId") Long orderId) {
        Order actual = orderRepository.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            actual.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            actual.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            actual.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            actual.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            actual.setDeliveryZip(patch.getDeliveryState());
        }
        if (patch.getCcNumber() != null) {
            actual.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            actual.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            actual.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(actual);
    }

    @DeleteMapping(path = "/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException ignore) {}
    }
}
