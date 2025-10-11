package Fiszki_database.Fiszki_Database.controllers;

import Fiszki_database.Fiszki_Database.services.WordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc

public class WordControllerIntegrationTest {

    private WordService wordService;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    public WordControllerIntegrationTest(WordService wordService, ObjectMapper objectMapper, MockMvc mockMvc) {
        this.wordService = wordService;
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    @Test
    public void testThatCreateWordSuccessfullyReturn201Created(){

    }
}
