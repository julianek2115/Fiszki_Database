package Fiszki_database.Fiszki_Database.repositories;

import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranslationRepository extends CrudRepository<TranslationEntity,String> {

    @Query("select t from TranslationEntity t where t.language = ?1")
    List<TranslationEntity> findAllByLanguage(String language);

    @Query("select w from TranslationEntity w where w.originalWord.word = ?1")
    List<TranslationEntity> findAllTranslationForWord(String word);

    void deleteByMeaning(String meaning);


}
