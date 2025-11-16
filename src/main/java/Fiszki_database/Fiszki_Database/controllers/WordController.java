package Fiszki_database.Fiszki_Database.controllers;

import Fiszki_database.Fiszki_Database.domain.DTO.WordDto;
import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import Fiszki_database.Fiszki_Database.mappers.Mapper;
import Fiszki_database.Fiszki_Database.services.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WordController {

    private WordService wordService;

    private Mapper<WordEntity, WordDto> wordMapper;

    public WordController(WordService wordService, Mapper<WordEntity, WordDto> wordMapper) {
        this.wordService = wordService;
        this.wordMapper = wordMapper;
    }

    @PostMapping(path = "/words")
    public ResponseEntity<WordDto> createWord(@RequestBody WordDto wordDto) {
        WordEntity wordEntity = wordMapper.mapFrom(wordDto);
        WordEntity savedWordEntity = wordService.save(wordEntity);
        WordDto savedWordDto = wordMapper.mapTo(savedWordEntity);
        return new ResponseEntity<>(savedWordDto, HttpStatus.CREATED);
    }

    //***LISTOWANIE WSZYSTKICH SŁÓW W BAZIE DANYCH***
    @GetMapping(path = "/words")
    public List<WordDto> listWords(){
        List<WordEntity> words = wordService.findAll();
        return words.stream()
                .map(wordMapper::mapTo)
                .collect(Collectors.toList());
    }

    //***LISTOWANIE SŁÓW FILTRUJĄC JE PO ICH KATEGORII
    @GetMapping(path = "/words/category/{category}")
    public List<WordDto> listWordsByCategory(@PathVariable("category") String category){
        List<WordEntity> foundWords = wordService.findAllByCategory(category);
        return  foundWords.stream()
                .map(wordMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/words/{word}")
    public ResponseEntity<WordDto> getWord(@PathVariable("word") String word){
        Optional<WordEntity> foundWord = wordService.findByWord(word);

        return foundWord.map(wordEntity ->{
            WordDto wordDto = wordMapper.mapTo(wordEntity);
            return new ResponseEntity<>(wordDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/words/id/{id}")
    public ResponseEntity<WordDto> deleteWordById(@PathVariable("id") Long id){
        wordService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/words/word/{word}")
    public ResponseEntity<WordDto> deleteWord(@PathVariable("word") String word){
        wordService.deleteWord(word);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
