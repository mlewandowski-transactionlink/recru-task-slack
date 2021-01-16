package com.insult.industries.insultapp.service;

import com.insult.industries.insultapp.external.InsultGeneratorClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultInsultService implements InsultService {
    private static final List<String> availableLanguages = List.of("en", "cn", "de", "el", "es", "fr", "ru", "sw");

    private final InsultGeneratorClient insultGeneratorClient;

    @Override
    public String createInsult(String language) {
        //No language specified - use default (English)
        if (language == null || language.isEmpty()) return insultGeneratorClient.getInsult();

        //Check if desired language is available, if not use default
        if (!availableLanguages.contains(language)) {
            log.warn("Language={} is not available in the generator!", language);
            return insultGeneratorClient.getInsult();
        }

        return insultGeneratorClient.getInsult(language);
    }
}
