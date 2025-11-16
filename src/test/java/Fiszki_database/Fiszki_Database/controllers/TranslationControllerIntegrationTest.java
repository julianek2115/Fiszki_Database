package Fiszki_database.Fiszki_Database.controllers;

import Fiszki_database.Fiszki_Database.TestDataUtil;
import Fiszki_database.Fiszki_Database.domain.DTO.TranslationDto;
import Fiszki_database.Fiszki_Database.services.TranslationService;
import Fiszki_database.Fiszki_Database.services.WordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        TranslationDto testTranslationDto = TestDataUtil.createTestTranslationDtoA(null);
        testTranslationDto.setId(null);
        String createTranslationJson = objectMapper.writeValueAsString(testTranslationDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/translations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createTranslationJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    //commit test

}
