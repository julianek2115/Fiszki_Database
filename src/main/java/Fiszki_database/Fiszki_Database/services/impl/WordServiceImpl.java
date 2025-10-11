package Fiszki_database.Fiszki_Database.services.impl;

import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import Fiszki_database.Fiszki_Database.repositories.WordRepository;
import Fiszki_database.Fiszki_Database.services.WordService;

public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public WordEntity save(WordEntity wordEntity) {
        return wordRepository.save(wordEntity);
    }
}
