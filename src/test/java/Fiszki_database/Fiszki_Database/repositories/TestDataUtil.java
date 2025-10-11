package Fiszki_database.Fiszki_Database.repositories;

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
}
