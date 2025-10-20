package Fiszki_database.Fiszki_Database.repositories;

import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends CrudRepository<WordEntity, Long> {

    Optional<WordEntity> findByWord(String word);

}


//20.10.2025