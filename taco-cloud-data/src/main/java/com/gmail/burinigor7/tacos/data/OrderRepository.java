package com.gmail.burinigor7.tacos.data;

import com.gmail.burinigor7.tacos.domain.Order;
import com.gmail.burinigor7.tacos.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository
        extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);

}