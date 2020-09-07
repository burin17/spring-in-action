package com.gmail.burinigor7.tacos.api;

import com.gmail.burinigor7.tacos.data.TacoRepository;
import com.gmail.burinigor7.tacos.domain.Taco;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/design",
                produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private TacoRepository tacoRepository;
    private EntityLinks entityLinks;

    public DesignTacoController(TacoRepository tacoRepository,
                                EntityLinks entityLinks) {
        this.tacoRepository = tacoRepository;
        this.entityLinks = entityLinks;
    }

//    @GetMapping("/recent")
//    public Iterable<Taco> recentTacos() {
//        PageRequest page = PageRequest.of(0, 12,
//                Sort.by("createdAt").descending());
//        return tacoRepository.findAll(page).getContent();
//    }

    @Bean
    public RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>
    tacoProcessor(EntityLinks links) {
        return new RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>() {
            @Override
            public PagedModel<EntityModel<Taco>> process(
                    PagedModel<EntityModel<Taco>> resource) {
                resource.add(
                        links.linkFor(Taco.class)
                                .slash("recent")
                                .withRel("recents"));
                return resource;
            }
        };
    }

    @GetMapping(path = "/recent")
    public CollectionModel<TacoModel> recentTacos() {
        PageRequest page = PageRequest.of(0, 12,
                Sort.by("createdAt").descending());
        List<Taco> rawTacos = tacoRepository.findAll(page).getContent();
        CollectionModel<TacoModel> tacoModels = new TacoModelAssembler()
                .toCollectionModel(rawTacos);
        tacoModels.add(
                linkTo(methodOn(this.getClass()).recentTacos())
                .withSelfRel()
        );
        return tacoModels;
    }

    @GetMapping("/{id}")
    public Taco findById(@PathVariable("id") Long id) {
        Optional<Taco> taco = tacoRepository.findById(id);
        return taco.orElse(null);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }
}