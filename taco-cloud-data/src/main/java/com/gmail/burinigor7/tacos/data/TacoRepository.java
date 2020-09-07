package com.gmail.burinigor7.tacos.data;

import com.gmail.burinigor7.tacos.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository
        extends PagingAndSortingRepository<Taco, Long> {

}
