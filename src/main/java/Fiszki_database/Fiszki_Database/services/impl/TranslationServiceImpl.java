package Fiszki_database.Fiszki_Database.services.impl;

import Fiszki_database.Fiszki_Database.repositories.TranslationRepository;
import Fiszki_database.Fiszki_Database.services.TranslationService;

public class TranslationServiceImpl implements TranslationService {

    private TranslationRepository translationRepository;

    public TranslationServiceImpl(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }
}
