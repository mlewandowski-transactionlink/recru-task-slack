package pl.nikowis.insult.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.nikowis.insult.service.InsultingGenerator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class InsultControllerTest {

    private String INSULT_VAL = "You are so ugly ...";

    @MockBean
    private InsultingGenerator insultingGenerator;

    @Autowired
    private SlackInsultController insultController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(insultController).build();
        when(insultingGenerator.generateInsult()).thenReturn(INSULT_VAL);
    }

    @Test
    void insultEndpointTest() throws Exception {
        this.mockMvc.perform(post(SlackInsultController.INSULT_ENDPOINT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response_type", is(notNullValue())))
                .andExpect(jsonPath("$.text", is(INSULT_VAL)));
    }
}