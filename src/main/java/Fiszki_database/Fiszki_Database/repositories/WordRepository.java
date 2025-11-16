package Fiszki_database.Fiszki_Database.repositories;

import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends CrudRepository<WordEntity, Long> {

    Optional<WordEntity> findByWord(String word);

    @Query("select w from WordEntity w where w.category = ?1")
    List<WordEntity> findAllByCategory(String category);

    void deleteByWord(String word);
}


//20.10.2025
//22.10.2025
//23/10.2025