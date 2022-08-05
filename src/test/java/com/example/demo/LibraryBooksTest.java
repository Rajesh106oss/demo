package com.example.demo;

import com.example.demo.validator.CreateBookInfo;
import com.example.demo.validator.CreateLibraryInfo;
import com.example.demo.validator.UpdateLibraryInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Library controller test")
public class LibraryBooksTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Create Library")
    void createLibrary() throws Exception {
        var library = new CreateLibraryInfo("AvLibrary",null);
        createLibrary(library, status().isOk());
        library.setName("av");
        createLibrary(library, status().isBadRequest());
    }

    public void createLibrary(CreateLibraryInfo library, ResultMatcher status)
            throws Exception {
        mockMvc.perform(post("/v1/libraries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(library)))
                .andExpect(status)
                .andDo(print());
    }

    @Test
    @DisplayName("Update Library")
    void updateLibrary() throws Exception {
        var createLibraryInfo = new CreateLibraryInfo("AvLibrary",null);
        createLibrary(createLibraryInfo, status().isOk());
        var libraryId = JsonPath.parse(mockMvc.perform(post("/v1/libraries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createLibraryInfo)))
                .andReturn().getResponse().getContentAsString()).read("id", Integer.class);
        var updateLibraryInfo = new UpdateLibraryInfo(null, "SkLibrary", null);
        updateLibrary(updateLibraryInfo, libraryId, status().isOk());
        updateLibraryInfo.setName("av");
        updateLibrary(updateLibraryInfo, libraryId, status().isBadRequest());
        updateLibraryInfo.setName(null);
        updateLibrary(updateLibraryInfo, libraryId, status().isBadRequest());
        updateLibraryInfo.setName("anna library");
        updateLibrary(updateLibraryInfo, 0, status().isBadRequest());
    }

    @Test
    @DisplayName("Get Library")
    void GetLibraryInfo() throws Exception {
        getLibraryInfo(1, status().isOk());
        getLibraryInfo(0, status().isBadRequest());
    }

    public void updateLibrary(UpdateLibraryInfo library, Integer libraryId, ResultMatcher status)
            throws Exception {
        mockMvc.perform(put("/v1/libraries/" + libraryId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(library)))
                .andExpect(status)
                .andDo(print());
    }

    @Test
    @DisplayName("Create Library Book")
    void CreateLibraryBook() throws Exception {
        var libraries = new CreateBookInfo("RkBooks", "ANR",null,null);
        createLibraryBook(libraries, status().isOk());
        libraries.setName("av");
        createLibraryBook(libraries, status().isBadRequest());
    }

    public void createLibraryBook(CreateBookInfo libraryBooks, ResultMatcher status)
            throws Exception {
        mockMvc.perform(post("/v1/libraries/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(libraryBooks)))
                .andExpect(status)
                .andDo(print());
    }

    private void getLibraryInfo(Integer libraryId, ResultMatcher status) throws Exception {
        mockMvc.perform(get("/v1/libraries/libraryId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(libraryId)))
                .andExpect(status)
                .andDo(print());
    }

}
