package Fiszki_database.Fiszki_Database.services;

import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import org.springframework.stereotype.Service;

@Service
public interface WordService {

    WordEntity save(WordEntity wordEntity);
}
