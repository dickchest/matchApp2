package com.match.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.match.domain.dto.UserStatusDto;
import com.match.domain.entity.enums.UserStatus;
import com.match.services.AnyoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
class AnyoneControllerFullTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws IOException, IllegalAccessException {
    }

    @Test
    @WithMockUser
    public void analyzeTest() throws Exception {
        MockMultipartFile photo = new MockMultipartFile("foto", "filename.txt", "text/plan", "some photo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/anyone/analyze")
                        .file(photo)
                        .param("name", "John Doe")
                        .param("dateOfBirth", "01-01-1990")
                        .param("gender", "1")
                        .param("relationshipType", "single"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ANALYSED"));
    }
}