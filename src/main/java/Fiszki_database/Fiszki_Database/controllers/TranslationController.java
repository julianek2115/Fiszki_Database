package Fiszki_database.Fiszki_Database.controllers;

import Fiszki_database.Fiszki_Database.domain.DTO.TranslationDto;
import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import Fiszki_database.Fiszki_Database.mappers.Mapper;
import Fiszki_database.Fiszki_Database.services.TranslationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

}
