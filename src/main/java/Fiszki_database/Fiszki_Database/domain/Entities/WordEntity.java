package Fiszki_database.Fiszki_Database.domain.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "words")
@JsonIgnoreProperties(ignoreUnknown = true)

public class WordEntity {

    @Id
    @GeneratedValue
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1
    )
    private Long id;

    private String word;

    private String category;

    @OneToMany(
            mappedBy = "originalWord",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<TranslationEntity> translations = new ArrayList<>();
}
