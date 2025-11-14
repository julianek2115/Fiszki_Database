package Fiszki_database.Fiszki_Database.services;

import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WordService {

    WordEntity save(WordEntity wordEntity);

    List<WordEntity> findAll();

    List<WordEntity> findAllByCategory(String category);

    Optional<WordEntity> findByWord(String word);

    void delete(Long id);

    void deleteWord(String word);


}
