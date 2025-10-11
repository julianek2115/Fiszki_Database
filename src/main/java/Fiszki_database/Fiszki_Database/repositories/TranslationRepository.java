package Fiszki_database.Fiszki_Database.repositories;

import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import org.springframework.data.repository.CrudRepository;

public interface TranslationRepository extends CrudRepository<TranslationEntity,Long> {
}
