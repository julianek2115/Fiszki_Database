package Fiszki_database.Fiszki_Database.controllers;

import Fiszki_database.Fiszki_Database.TestDataUtil;
import Fiszki_database.Fiszki_Database.domain.DTO.TranslationDto;
import Fiszki_database.Fiszki_Database.domain.DTO.WordDto;
import Fiszki_database.Fiszki_Database.domain.Entities.TranslationEntity;
import Fiszki_database.Fiszki_Database.services.TranslationService;
import Fiszki_database.Fiszki_Database.services.WordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc

public class TranslationControllerIntegrationTest {

    private TranslationService translationService;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Autowired

    public TranslationControllerIntegrationTest(TranslationService translationService, ObjectMapper objectMapper, MockMvc mockMvc) {
        this.translationService = translationService;
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    @Test
    public void testThatCreateTranslationReturnsHttpStatus201Created() throws Exception {
        WordDto testWordDtoA = TestDataUtil.createTestWordDtoA();
        TranslationDto testTranslation = TestDataUtil.createTestTranslationDtoA(testWordDtoA);
        String createTranslationJson = objectMapper.writeValueAsString(testTranslation);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testWordDtoA))
        );

        mockMvc.perform(
                MockMvcRequestBuilders.put("/translations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createTranslationJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatUpdateTranslationReturnsHttpStatus200Ok() throws Exception {
        WordDto testWordDtoA = TestDataUtil.createTestWordDtoA();
        TranslationEntity testTranslationA = TestDataUtil.createTestTranslationEntityA(null);
        TranslationEntity savedTranslation = translationService.createTranslation(testTranslationA);

        TranslationDto translationDto = TestDataUtil.createTestTranslationDtoA(testWordDtoA);
        translationDto.setMeaning(savedTranslation.getMeaning());
        String createTranslationJson = objectMapper.writeValueAsString(translationDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testWordDtoA))
        );

        mockMvc.perform(
                MockMvcRequestBuilders.put("/translations/" + translationDto.getMeaning())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createTranslationJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }


    @Test
    public void testThatCreateTranslationReturnsCreatedTranslation() throws Exception {
        TranslationDto testTranslationDto = TestDataUtil.createTestTranslationDtoA(null);
        String createTranslationJson = objectMapper.writeValueAsString(testTranslationDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/translations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createTranslationJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.meaning").value(testTranslationDto.getMeaning())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.language").value(testTranslationDto.getLanguage())
        );

    }

    @Test
    public void testThatUpdateTranslationReturnsHttpUpdateBook() throws Exception {
        WordDto testWordDtoA = TestDataUtil.createTestWordDtoA();
        TranslationEntity testTranslationA = TestDataUtil.createTestTranslationEntityA(null);
        TranslationEntity savedTranslation = translationService.createTranslation(testTranslationA);

        TranslationDto translationDto = TestDataUtil.createTestTranslationDtoA(testWordDtoA);
        translationDto.setMeaning(savedTranslation.getMeaning());
        translationDto.setLanguage("UPDATED");
        String createTranslationJson = objectMapper.writeValueAsString(translationDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testWordDtoA))
        );

        mockMvc.perform(
                MockMvcRequestBuilders.put("/translations/" + savedTranslation.getMeaning())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createTranslationJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.meaning").value("head")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.language").value("UPDATED")
        );
    }

    @Test
    public void testThatListTranslationsSuccessfullyReturnsHttp200Created() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/translations")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }


    @Test
    public void testThatListTranslationsReturnsListOfTranslations() throws Exception {
        TranslationEntity testTranslationA = TestDataUtil.createTestTranslationEntityA(null);
        translationService.createTranslation(testTranslationA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/translations")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].language").value("en")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].meaning").value("head")
        );
    }

    @Test
    public void testThatDeleteTranslationByMeaningReturnsHttp204ForNonExistingTranslation() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/translations/translation/abcd")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteTranslationByMeaningReturnsHttpStatusForExistingTranslation() throws Exception {
        TranslationEntity testTranslationA = TestDataUtil.createTestTranslationEntityA(null);
        translationService.createTranslation(testTranslationA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/translations/translation/" + testTranslationA.getMeaning())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
