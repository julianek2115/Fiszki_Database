package Fiszki_database.Fiszki_Database.services.impl;

import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import Fiszki_database.Fiszki_Database.repositories.TranslationRepository;
import Fiszki_database.Fiszki_Database.services.TranslationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TranslationServiceImpl implements TranslationService {

    private TranslationRepository translationRepository;

    public TranslationServiceImpl(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    public TranslationEntity createUpdateTranslation(String meaning, TranslationEntity translation) {
        return translationRepository.save(translation);
    }

    @Override
    public List<TranslationEntity> findAll() {
        return StreamSupport.stream(translationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteTranslation(String meaning) {
        translationRepository.deleteByMeaning(meaning);
    }

    @Override
    public boolean isExists(String meaning) {
        return translationRepository.existsById(meaning);
    }

    @Override
    public TranslationEntity partialUpdate(String meaning, TranslationEntity translation) {
        translation.setMeaning(meaning);
        return translationRepository.findById(meaning).map(existingTranslation ->{
            Optional.ofNullable(translation.getLanguage()).ifPresent(existingTranslation::setLanguage);
            return translationRepository.save(existingTranslation);
        }).orElseThrow(() -> new RuntimeException("Translation does not exist!"));
    }

    @Override
    public List<TranslationEntity> findAllByLanguage(String language) {
        return translationRepository.findAllByLanguage(language);
    }

    @Override
    public List<TranslationEntity> findAllTranslationsForWord(String word) {
        return List.of();
    }

//    @Override
//    public Optional<TranslationEntity> findTranslationByMeaning(String meaning) {
//        return translationRepository.findTranslationByMeaning(meaning);
//    }

}
