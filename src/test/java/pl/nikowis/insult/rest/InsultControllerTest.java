package pl.nikowis.insult.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.nikowis.insult.service.InsultingService;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class InsultControllerTest {

    private String INSULT_VAL = "You are so ugly ...";

    @MockBean
    private InsultingService insultingService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        when(insultingService.generateInsult()).thenReturn(INSULT_VAL);
    }

    @Test
    void insultEndpointTest() throws Exception {
        this.mockMvc.perform(post(InsultController.INSULT_ENDPOINT))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(INSULT_VAL)));
    }
}