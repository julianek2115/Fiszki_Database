package Fiszki_database.Fiszki_Database.services;

import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TranslationService {

    TranslationEntity createUpdateTranslation(String meaning, TranslationEntity translation);

    List<TranslationEntity> findAll();

    //Optional<TranslationEntity> findTranslationByMeaning(String meaning);

    void deleteTranslation(String meaning);

    boolean isExists(String meaning);

    TranslationEntity partialUpdate(String meaning, TranslationEntity translation);

    List<TranslationEntity> findAllByLanguage(String language);

    List<TranslationEntity> findAllTranslationsForWord(String word);

}
