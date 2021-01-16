package com.insult.industries.insultapp.api;

import com.insult.industries.insultapp.service.InsultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InsultResource.class)
public class InsultResourceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    InsultService insultService;

    @Test
    public void createsInsult() throws Exception {
        String expectedInsult = "Llama";
        when(insultService.createInsult(null)).thenReturn(expectedInsult);

        mvc.perform(post("/insult")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedInsult));
    }

    @Test
    public void createsInsultInSpanish() throws Exception {
        String expectedInsult = "Caraculo";
        when(insultService.createInsult("es")).thenReturn(expectedInsult);

        mvc.perform(post("/insult")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("text=es"))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedInsult));
    }
}
