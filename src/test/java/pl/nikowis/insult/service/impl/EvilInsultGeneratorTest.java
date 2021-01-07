package pl.nikowis.insult.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EvilInsultGeneratorTest {

    private EvilInsultGenerator service;

    private String INSULT_URL = "https://testurl.com/generate";
    private String INSULT_VAL = "You are so ugly ...";
    private RestTemplate restTemplateMock;

    @BeforeEach
    void setUp() {
        restTemplateMock = Mockito.mock(RestTemplate.class);
        EvilInsultGenerator.InsultDTO apiResponse = new EvilInsultGenerator.InsultDTO();
        apiResponse.setInsult(INSULT_VAL);
        when(restTemplateMock.getForObject(eq(INSULT_URL), eq(EvilInsultGenerator.InsultDTO.class))).thenReturn(apiResponse);
    }

    @Test
    void testGenerateInsultCallsApi() {
        service = new EvilInsultGenerator(restTemplateMock, INSULT_URL);

        String insult = service.generateInsult();

        verify(restTemplateMock, times(1)).getForObject(any(String.class), any(Class.class));
        assertEquals(INSULT_VAL, insult);
    }
}