package Fiszki_database.Fiszki_Database.controllers;

import Fiszki_database.Fiszki_Database.domain.DTO.WordDto;
import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import Fiszki_database.Fiszki_Database.mappers.Mapper;
import Fiszki_database.Fiszki_Database.services.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
