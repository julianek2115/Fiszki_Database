package Fiszki_database.Fiszki_Database.mappers.impl;

import Fiszki_database.Fiszki_Database.domain.DTO.WordDto;
import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import Fiszki_database.Fiszki_Database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WordMapperImpl implements Mapper<WordEntity, WordDto> {

    private ModelMapper modelMapper;

    public WordMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WordDto mapTo(WordEntity wordEntity) {
        return modelMapper.map(wordEntity, WordDto.class);
    }

    @Override
    public WordEntity mapFrom(WordDto wordDto) {
        return modelMapper.map(wordDto, WordEntity.class);
    }
}
