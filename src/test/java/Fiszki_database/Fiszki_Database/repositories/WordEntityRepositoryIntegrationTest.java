package Fiszki_database.Fiszki_Database.repositories;

import Fiszki_database.Fiszki_Database.TestDataUtil;
import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableTransactionManagement
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

    @Test
    public void testThatWordCanBeDeletedAndRecalledById(){
        WordEntity wordEntity = TestDataUtil.createTestWordEntityA();
        wordRepository.save(wordEntity);
        wordRepository.deleteById(wordEntity.getId());
        Optional<WordEntity> resultByWord = wordRepository.findById(wordEntity.getId());
        assertThat(resultByWord).isEmpty();
    }

    @Test
    @Transactional
    public void testThatWordCanBeDeletedAndRecalledByWord(){
        WordEntity wordEntity = TestDataUtil.createTestWordEntityA();
        wordRepository.save(wordEntity);
        wordRepository.deleteByWord(wordEntity.getWord());
        Optional<WordEntity> resultByWord = wordRepository.findByWord(wordEntity.getWord());
        assertThat(resultByWord).isEmpty();
    }

    @Test
    public void testThatWordsCanBeFoundByCategory(){
        WordEntity wordEntity = TestDataUtil.createTestWordEntityA();
        wordRepository.save(wordEntity);
        List<WordEntity> foundWords = wordRepository.findAllByCategory(wordEntity.getCategory());
        assertThat(foundWords).hasSize(1);
    }
}
