package app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AppRunnerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {

    }

    @Test
    @DisplayName("Test change cat")
    public void changeCatTest() throws Exception {
        String cat = "{" +
                "	\"id\": \"1\"," +
                "	\"name\": \"Onessa\"," +
                "	\"age\": \"1\"," +
                "	\"weight\": \"1\"" +
                "}";

        mockMvc.perform(put("/change")
                        .content(cat)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test create cat")
    public void createCatTest() throws Exception {
        String cat = "{" +
                "	\"id\": \"\"," +
                "	\"name\": \"\"," +
                "	\"age\": \"\"," +
                "	\"weight\": \"\"" +
                "}";

        mockMvc.perform(post("/create")
                        .content(cat)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test delete cat")
    public void deleteCatTest() throws Exception {
        String id = "5";

        mockMvc.perform(delete("/delete")
                        .param("id", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test get all cat")
    public void getAllCatTest() throws Exception {
        mockMvc.perform(get("/getAll"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test get cat by id")
    public void getCatByIdTest() throws Exception {
        String id = "1";

        mockMvc.perform(get("/getById")
                        .param("id", id))
                .andExpect(status().isOk())
                .andDo(print());
    }
}