package Fiszki_database.Fiszki_Database.domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "translations")

public class TranslationEntity {

    @Id
    private String meaning;

    private String language;

    @ManyToOne(cascade = CascadeType.ALL)
    //@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "original_word")
    private WordEntity originalWord;
}

