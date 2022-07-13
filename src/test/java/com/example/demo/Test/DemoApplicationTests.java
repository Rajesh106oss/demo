package com.example.demo.Test;

import com.example.demo.validator.CreateLibraryInfo;
import com.example.demo.validator.UpdateLibraryInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Library controller test")
class DemoApplicationTests {
    private final MockMvc mockMvc;

    DemoApplicationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("Create Library")
    void createLibrary() throws Exception {
        var library = new CreateLibraryInfo("Martin", "Louis",
                "Ash", null, 200, null);
        createLibrary(library, status().isBadRequest());
        createLibrary(library, status().isBadRequest());
        createLibrary(library, status().isBadRequest());
    }

    public void createLibrary(CreateLibraryInfo library, ResultMatcher status) throws Exception {
        mockMvc.perform(post("/libraries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(library)))
                .andExpect(status)
                .andDo(print());
    }

    @Test
    @DisplayName("Update Library")
    void updateLibrary() throws Exception {
        var library = new UpdateLibraryInfo(null, "AN", "km",
                "ash", null, 500, null);
        updateLibrary(library, status().isBadRequest());
        updateLibrary(library, status().isBadRequest());
        updateLibrary(library, status().isBadRequest());
    }

    public void updateLibrary(UpdateLibraryInfo library, ResultMatcher status)
            throws Exception {
        mockMvc.perform(put("/libraries/ libraryId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(library)))
                .andExpect(status)
                .andDo(print());
    }
}
