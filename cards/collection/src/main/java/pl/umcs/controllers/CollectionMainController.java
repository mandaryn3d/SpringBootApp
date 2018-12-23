package pl.umcs.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.datatypes.Collection;
import pl.umcs.services.CollectionService;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/collection")
public class CollectionMainController {

    private static final Logger log = LoggerFactory.getLogger(CollectionMainController.class);
    private CollectionService collectionService;

    @Autowired
    public CollectionMainController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }
    @GetMapping("/hello")
    public String getHello() {
        return "Hello. This is the Collection speaking.";
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Collection> getCollections() {
        List<Collection> collections = collectionService.findAll();

        log.info("Retrieve objects {}", collections);

        return collections;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Collection save(@RequestBody Collection user) {
        Collection savedCollection = collectionService.save(user);

        log.info("Add Collection {}", savedCollection);

        return savedCollection;
    }

    @GetMapping("/{id}")
    public Collection find(@PathVariable Long id) {
        return collectionService.find(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);

        log.info("Delete Collection with id {}", id);

        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public Collection update(@RequestBody Collection collection) {
        Collection updatedCollection = collectionService.update(collection);

        log.info("Updated Collection {}", updatedCollection);

        return updatedCollection;
    }
}
