package Fiszki_database.Fiszki_Database.services.impl;

import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import Fiszki_database.Fiszki_Database.repositories.WordRepository;
import Fiszki_database.Fiszki_Database.services.WordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public WordEntity save(WordEntity wordEntity) {
        return wordRepository.save(wordEntity);
    }

    @Override
    public List<WordEntity> findAll() {
        return StreamSupport.stream(wordRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<WordEntity> findAllByCategory(String category) {
        return wordRepository.findAllByCategory(category);
    }

    @Override
    public Optional<WordEntity> findByWord(String word) {
        return wordRepository.findByWord(word);
    }

    @Override
    public void delete(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteWord(String word) {
        wordRepository.deleteByWord(word);
    }


}
