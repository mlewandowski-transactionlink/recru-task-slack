package pl.nikowis.insult.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.nikowis.insult.service.InsultingService;

@RestController
public class InsultController {

    public static final String INSULT_ENDPOINT = "/insult";

    @Autowired
    private InsultingService insultingService;

    @PostMapping(INSULT_ENDPOINT)
    public String insult() {
        return insultingService.generateInsult();
    }

}


