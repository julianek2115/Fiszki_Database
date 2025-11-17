package Fiszki_database.Fiszki_Database.services.impl;

import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import Fiszki_database.Fiszki_Database.repositories.TranslationRepository;
import Fiszki_database.Fiszki_Database.services.TranslationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TranslationServiceImpl implements TranslationService {

    private TranslationRepository translationRepository;

    public TranslationServiceImpl(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    public TranslationEntity createTranslation(TranslationEntity translation) {
        return translationRepository.save(translation);
    }

    @Override
    public List<TranslationEntity> findAll() {
        return StreamSupport.stream(translationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

}
