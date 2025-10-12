package Fiszki_database.Fiszki_Database.mappers.impl;

import Fiszki_database.Fiszki_Database.domain.DTO.TranslationDto;
import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import Fiszki_database.Fiszki_Database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TranslationMapperImpl implements Mapper<TranslationEntity, TranslationDto> {

    private ModelMapper modelMapper;

    public TranslationMapperImpl() {
        this.modelMapper = modelMapper;
    }

    @Override
    public TranslationDto mapTo(TranslationEntity translationEntity) {
        return modelMapper.map(translationEntity, TranslationDto.class);
    }

    @Override
    public TranslationEntity mapFrom(TranslationDto translationDto) {
        return modelMapper.map(translationDto, TranslationEntity.class);
    }
}
