package Fiszki_database.Fiszki_Database.repositories;

import Fiszki_database.Fiszki_Database.TestDataUtil;
import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class TranslationEntityRepositoryIntegrationTest {

    private WordRepository wordRepository;

    private TranslationRepository translationRepositoryTest;

    @Autowired
    public TranslationEntityRepositoryIntegrationTest(WordRepository wordRepository, TranslationRepository translationRepository) {
        this.wordRepository = wordRepository;
        this.translationRepositoryTest = translationRepository;
    }

    @Test
    public void testThatTranslationCanBeCreatedAndRecalled(){
        WordEntity wordEntity = TestDataUtil.createTestWordEntityA();
        TranslationEntity translationEntity = TestDataUtil.createTestTranslationEntityA(wordEntity);

        translationRepositoryTest.save(translationEntity);
        Optional<TranslationEntity> result = translationRepositoryTest.findById(translationEntity.getMeaning());
        assertThat(result).isPresent();
        assertThat(result.get().getLanguage()).isEqualTo(translationEntity.getLanguage());
    }

    @Test
    @Transactional
    public void testThatTranslationCanBeDeletedAndRecalled(){
        TranslationEntity testTranslationEntityA = TestDataUtil.createTestTranslationEntityA(null);
        translationRepositoryTest.save(testTranslationEntityA);
        translationRepositoryTest.deleteByMeaning(testTranslationEntityA.getMeaning());
        Optional<TranslationEntity> result = translationRepositoryTest.findById(testTranslationEntityA.getMeaning());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatTranslationExists(){
        TranslationEntity testTranslationEntityA = TestDataUtil.createTestTranslationEntityA(null);
        translationRepositoryTest.save(testTranslationEntityA);
        boolean existByMeaning = translationRepositoryTest.existsById(testTranslationEntityA.getMeaning());
        assertThat(existByMeaning).isTrue();
    }

    @Test
    public void testThatTranslationsCanBeFoundByLanguage(){
        WordEntity testWordEntityA = TestDataUtil.createTestWordEntityA();
        TranslationEntity testTranslationEntityA = TestDataUtil.createTestTranslationEntityA(testWordEntityA);

        translationRepositoryTest.save(testTranslationEntityA);
        List<TranslationEntity> allByLanguage = translationRepositoryTest.findAllByLanguage(testTranslationEntityA.getLanguage());
        assertThat(allByLanguage).hasSize(1);
    }

    @Test
    public void testThatTranslationsCanBeFoundByuOriginalWord(){
        WordEntity testWordEntityA = TestDataUtil.createTestWordEntityA();
        TranslationEntity testTranslationEntityA = TestDataUtil.createTestTranslationEntityA(testWordEntityA);

        translationRepositoryTest.save(testTranslationEntityA);
        List<TranslationEntity> allTranslationForWord = translationRepositoryTest.findAllTranslationForWord(testWordEntityA.getWord());
        assertThat(allTranslationForWord).hasSize(1);
    }


}
