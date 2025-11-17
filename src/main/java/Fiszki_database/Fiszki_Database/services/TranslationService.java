package Fiszki_database.Fiszki_Database.services;

import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TranslationService {

    TranslationEntity createTranslation(TranslationEntity translation);

    List<TranslationEntity> findAll();
}
