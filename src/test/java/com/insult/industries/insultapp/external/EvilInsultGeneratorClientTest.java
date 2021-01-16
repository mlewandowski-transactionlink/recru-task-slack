package com.insult.industries.insultapp.external;

import com.insult.industries.insultapp.dto.EvilInsultGeneratorResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class EvilInsultGeneratorClientTest {
    @MockBean
    RestTemplate restTemplate;

    EvilInsultGeneratorClient evilInsultGeneratorClient;

    @Before
    public void before() {
        evilInsultGeneratorClient = new EvilInsultGeneratorClient(restTemplate);
    }

    @Test
    public void returnsInsult() {
        String expectedInsult = "Llama";
        EvilInsultGeneratorResponse response = new EvilInsultGeneratorResponse();
        response.setInsult(expectedInsult);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(response);

        String insult = evilInsultGeneratorClient.getInsult();

        assertEquals(insult, expectedInsult);
        verify(restTemplate, times(1)).getForObject(anyString(), any());
    }

    @Test
    public void returnsDefaultInsultOnHttpErrors() {
        String expectedInsult = "You're so horrible even my bot is not able to generate a proper insult!";
        when(restTemplate.getForObject(anyString(), any())).thenThrow(new RestClientException("Internal Server Error"));

        String insult = evilInsultGeneratorClient.getInsult();

        assertEquals(insult, expectedInsult);
        verify(restTemplate, times(1)).getForObject(anyString(), any());
    }
}
