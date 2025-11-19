package Fiszki_database.Fiszki_Database.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TranslationDto {

    private Long id;

    private String language;

    private String meaning;

    private String originalWord;


}
