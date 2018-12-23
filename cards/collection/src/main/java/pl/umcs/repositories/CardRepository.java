package pl.umcs.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.datatypes.Card;

public interface CardRepository extends CrudRepository<Card, Long> {
}
