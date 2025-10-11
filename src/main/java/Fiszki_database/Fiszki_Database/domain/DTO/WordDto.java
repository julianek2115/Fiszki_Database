package Fiszki_database.Fiszki_Database.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WordDto {

    private Long id;

    private String word;

    private String category;

}
