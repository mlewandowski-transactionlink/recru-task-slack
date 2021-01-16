package com.insult.industries.insultapp.external;

import com.insult.industries.insultapp.dto.EvilInsultGeneratorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@Component
@Slf4j
public class EvilInsultGeneratorClient implements InsultGeneratorClient {
    private static final String EVIL_INSULT_GENERATOR_SCHEME = "https";
    private static final String EVIL_INSULT_GENERATOR_HOST = "www.evilinsult.com";
    private static final String EVIL_INSULT_GENERATOR_PATH = "/generate_insult.php";

    private final RestTemplate restTemplate;

    @Override
    public String getInsult() {
        return getInsult("en");
    }

    @Override
    public String getInsult(String language) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(EVIL_INSULT_GENERATOR_SCHEME)
                .host(EVIL_INSULT_GENERATOR_HOST)
                .path(EVIL_INSULT_GENERATOR_PATH)
                .queryParam("lang", language)
                .queryParam("type", "json")
                .build();

        String insult;
        try {
            EvilInsultGeneratorResponse evilInsultGeneratorResponse = restTemplate.getForObject(uriComponents.toUriString(), EvilInsultGeneratorResponse.class);
            assert evilInsultGeneratorResponse != null;
            insult = evilInsultGeneratorResponse.getInsult();
        } catch (RestClientException e) {
            //Use a default message when call to Evil Insult Generator fails
            log.warn("Unable to retrieve insult from Evil Insult Generator, message={}", e.getMessage());
            insult = "You're so horrible even my bot is not able to generate a proper insult!";
        }

        return insult;
    }

}
