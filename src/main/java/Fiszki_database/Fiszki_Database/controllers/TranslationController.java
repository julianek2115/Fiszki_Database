package Fiszki_database.Fiszki_Database.controllers;

import Fiszki_database.Fiszki_Database.domain.DTO.TranslationDto;
import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import Fiszki_database.Fiszki_Database.mappers.Mapper;
import Fiszki_database.Fiszki_Database.services.TranslationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class TranslationController {

    private TranslationService translationService;

    private Mapper<TranslationEntity, TranslationDto>  translationMapper;

    public TranslationController(TranslationService translationService, Mapper<TranslationEntity, TranslationDto> translationMapper) {
        this.translationService = translationService;
        this.translationMapper = translationMapper;
    }

    @PutMapping(path = "/translations")
    public ResponseEntity<TranslationDto> addTranslation(@RequestBody TranslationDto translationDto){

        TranslationEntity translationEntity = translationMapper.mapFrom(translationDto);
        TranslationEntity savedTranslationEntity = translationService.createTranslation(translationEntity);
        TranslationDto savedTranslationDto = translationMapper.mapTo(savedTranslationEntity);
        return new ResponseEntity<>(savedTranslationDto, HttpStatus.CREATED);

    }

    @GetMapping(path = "/translations")
    public List<TranslationDto> listTranslations(){
        List<TranslationEntity> translations = translationService.findAll();
        return translations.stream()
                .map(x -> translationMapper.mapTo(x))
                .collect(Collectors.toList());
    }

//    @GetMapping(path = "/translations/{meaning}")
//    public ResponseEntity<TranslationDto> getTranslation(@PathVariable("meaning") String meaning){
//
//        Optional<TranslationEntity> foundTranslation = translationService.findTranslationByMeaning(meaning);
//
//        return foundTranslation.map(x -> {
//            TranslationDto translationDto = translationMapper.mapTo(x);
//            return new ResponseEntity<>(translationDto, HttpStatus.OK);
//            }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//
//    }

    @DeleteMapping(path = "/translations/translation/{meaning}")
    public ResponseEntity<TranslationDto> deleteTranslation(@PathVariable("meaning") String meaning){
        translationService.deleteTranslation(meaning);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
