package Fiszki_database.Fiszki_Database;

import Fiszki_database.Fiszki_Database.domain.DTO.WordDto;
import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;

public class TestDataUtil {
    private TestDataUtil() {

    }

    public static WordEntity createTestWordEntityA(){
        return WordEntity.builder()
                .word("głowa")
                .category("czesci_ciala")
                .build();
    }

    public static WordDto createTestWordDtoA(){
        return WordDto.builder()
                .word("głowa")
                .category("czesci_ciala")
                .build();
    }

    public static WordEntity createTestWordEntityB(){
        return WordEntity.builder()
                .word("ogórek")
                .category("warzywa")
                .build();
    }

    public static WordDto createTestWordDtoB(){
        return WordDto.builder()
                .word("ogórek")
                .category("warzywa")
                .build();
    }

    public static WordEntity createTestWordEntityC(){
        return WordEntity.builder()
                .word("piłka")
                .category("sport")
                .build();
    }

    public static WordDto createTestWordDtoC(){
        return WordDto.builder()
                .word("piłka")
                .category("sport")
                .build();
    }
}
