package Fiszki_database.Fiszki_Database.repositories;

import Fiszki_database.Fiszki_Database.TestDataUtil;
import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)


public class WordEntityRepositoryIntegrationTest {

    private final WordRepository wordRepository;

    @Autowired
    public WordEntityRepositoryIntegrationTest(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Test
    public void testThatGetSpecificWordWorks(){
        WordEntity wordEntity = TestDataUtil.createTestWordEntityA();
        wordRepository.save(wordEntity);

        Optional<WordEntity> resultByWord = wordRepository.findByWord("głowa");
        assertThat(resultByWord.isPresent());
        assertThat(resultByWord.get()).isEqualTo(wordEntity);
    }
}
