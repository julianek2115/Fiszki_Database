package Fiszki_database.Fiszki_Database.services;

import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import org.springframework.stereotype.Service;

@Service
public interface TranslationService {

    TranslationEntity createTranslation(TranslationEntity translation);

}
