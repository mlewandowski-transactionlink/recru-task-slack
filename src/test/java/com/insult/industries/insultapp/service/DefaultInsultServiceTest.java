package com.insult.industries.insultapp.service;

import com.insult.industries.insultapp.external.InsultGeneratorClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class DefaultInsultServiceTest {
    @MockBean
    InsultGeneratorClient insultGeneratorClient;

    DefaultInsultService defaultInsultService;

    @Before
    public void before() {
        defaultInsultService = new DefaultInsultService(insultGeneratorClient);
    }

    @Test
    public void createsInsultWhenLanguageIsNull() {
        String expectedInsult = "Llama";
        when(insultGeneratorClient.getInsult()).thenReturn(expectedInsult);

        String insult = defaultInsultService.createInsult(null);

        assertEquals(insult, expectedInsult);
        verify(insultGeneratorClient, times(1)).getInsult();
    }

    @Test
    public void createsInsultWhenLanguageIsSpecified() {
        String expectedInsult = "Caraculo";
        String spanish = "es";
        when(insultGeneratorClient.getInsult(eq(spanish))).thenReturn(expectedInsult);

        String insult = defaultInsultService.createInsult(spanish);

        assertEquals(insult, expectedInsult);
        verify(insultGeneratorClient, times(1)).getInsult(eq(spanish));
    }

    @Test
    public void createsInsultInDefaultLanguageIfSpecifiedLanguageIsUnavailable() {
        String expectedInsult = "Llama";
        String greek = "gr";
        when(insultGeneratorClient.getInsult()).thenReturn(expectedInsult);

        String insult = defaultInsultService.createInsult(greek);

        assertEquals(insult, expectedInsult);
        verify(insultGeneratorClient, times(1)).getInsult();
    }
}
