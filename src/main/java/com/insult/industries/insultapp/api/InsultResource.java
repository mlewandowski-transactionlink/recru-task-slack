package com.insult.industries.insultapp.api;

import com.insult.industries.insultapp.service.InsultService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insult")
@AllArgsConstructor
public class InsultResource {
    private final InsultService insultService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createInsult(SlackRequestPayload slackRequestPayload) {
        return insultService.createInsult(slackRequestPayload.getText());
    }

    @Data
    private static class SlackRequestPayload {
        String text;
    }
}
