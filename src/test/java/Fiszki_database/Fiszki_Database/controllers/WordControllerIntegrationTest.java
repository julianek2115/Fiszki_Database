package Fiszki_database.Fiszki_Database.controllers;

import Fiszki_database.Fiszki_Database.domain.Entities.WordEntity;
import Fiszki_database.Fiszki_Database.TestDataUtil;
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

public class WordControllerIntegrationTest {

    private WordService wordService;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Autowired
    public WordControllerIntegrationTest(WordService wordService, ObjectMapper objectMapper, MockMvc mockMvc) {
        this.wordService = wordService;
        this.objectMapper = new ObjectMapper();
        this.mockMvc = mockMvc;
    }

    @Test
    public void testThatCreateWordSuccessfullyReturn201Created() throws Exception {
        WordEntity testWordEntityA = TestDataUtil.createTestWordEntityA();
        testWordEntityA.setId(null);
        String wordJson = objectMapper.writeValueAsString(testWordEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wordJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateWordSuccessfullyReturnSavedWord() throws Exception{
        WordEntity testWordEntityA = TestDataUtil.createTestWordEntityA();
        testWordEntityA.setId(null);
        String wordJson = objectMapper.writeValueAsString(testWordEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wordJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.word").value("głowa")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.category").value("czesci_ciala")
        );

    }

    @Test
    public void testThatListWordsSuccessfullyReturnsHttp200Created() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/words")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}
