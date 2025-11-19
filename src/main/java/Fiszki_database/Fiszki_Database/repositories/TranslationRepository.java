package Fiszki_database.Fiszki_Database.repositories;

import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepository extends CrudRepository<TranslationEntity,Long> {

    Optional<TranslationEntity> findTranslationByMeaning(String meaning);

    void deleteByMeaning(String meaning);

}
