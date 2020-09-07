package com.gmail.burinigor7.tacos.data;

import com.gmail.burinigor7.tacos.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
