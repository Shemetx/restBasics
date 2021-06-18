package com.epam.esm;

import com.epam.esm.domain.Tag;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
@ActiveProfiles("dev")
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void notAuthorizedExceptionTest() throws Exception {
        mockMvc.perform(get("/tags"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void tokenExpiredExceptionTest() throws Exception {
        String EXPIRED_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjIxNjE3NTYxLCJleHAiOjE2MjE2MjExNjF9.RXFHqXLhGWfggw1HZStgxSUlJfaZzOP1WdvV1BiWunY";
        this.mockMvc.perform(get("/tags")
                .header("Authorization", EXPIRED_TOKEN))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @WithUserDetails()
    @Test
    public void authorityDeniedException() throws Exception {
        mockMvc.perform(post("/tags").param("name", "testTag"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @WithUserDetails()
    @Test
    public void userAuthorityTest() throws Exception {
        mockMvc.perform(get("/tags"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithUserDetails("admin")
    @Test
    public void adminAuthorityTest() throws Exception {
        Tag tag = new Tag();
        tag.setName("testTag");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String s = ow.writeValueAsString(tag);

        mockMvc.perform(post("/tags").contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isCreated());
    }


}
