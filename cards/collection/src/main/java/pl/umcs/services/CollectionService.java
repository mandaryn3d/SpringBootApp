package pl.umcs.services;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.umcs.datatypes.Collection;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionService {
    private static final Logger log = LoggerFactory.getLogger(CollectionService.class);

    public Collection save(Collection collection) {
        Collection savedCollection = collection;//collectionRepository.save(collection);

        log.info("Successfull collection Saved: " + savedCollection.getId());

        return savedCollection;
    }

    public List<Collection> findAll() {
        //Iterable<Collection> collectionIterable = collectionRepository.findAll();
        List<Collection> collections = Lists.newArrayList();//collectionIterable

        log.info("Returning all collections");
        return collections;
    }

    public Collection find(Long id) {
        //Optional<Collection> employee = collectionRepository.findById(id);
        //return employee.orElse(null);
        log.info("Returning collection: " + id);
        return null;
    }

    public Collection update(Collection collection) {
        Collection updatedCollection = collection;//collectionRepository.save(collection);

        log.info("Successfull collection Update: " + updatedCollection.getId());

        return updatedCollection;
    }

    public void deleteCollection(Long id) {
        //collectionRepository.deleteById(id);

        log.info("Successfull employee Delete: " +  id);
    }
}
