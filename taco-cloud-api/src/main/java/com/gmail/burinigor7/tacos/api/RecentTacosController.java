package com.gmail.burinigor7.tacos.api;

import com.gmail.burinigor7.tacos.data.TacoRepository;
import com.gmail.burinigor7.tacos.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
@RequestMapping
public class RecentTacosController {
    private TacoRepository tacoRepository;

    @Autowired
    public RecentTacosController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoModel>> recentTacos() {
        PageRequest pageRequest =
                PageRequest.of(0, 12, Sort.by("createdAt")
                        .descending());
        List<Taco> rawTacos = tacoRepository.findAll(pageRequest)
                .getContent();
        CollectionModel<TacoModel> tacoModels =
                new TacoModelAssembler().toCollectionModel(rawTacos);
        tacoModels.add(linkTo(methodOn(this.getClass())
                .recentTacos()).withSelfRel());

        return new ResponseEntity<>(tacoModels, HttpStatus.OK);
    }
}
