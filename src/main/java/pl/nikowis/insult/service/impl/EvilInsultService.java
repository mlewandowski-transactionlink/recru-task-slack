package pl.nikowis.insult.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.nikowis.insult.service.InsultingService;

@Service
class EvilInsultService implements InsultingService {

    private String insultGeneratorUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public EvilInsultService(RestTemplate restTemplate, @Value("${evil.insult.generator.url}") String insultGeneratorUrl) {
        this.restTemplate = restTemplate;
        this.insultGeneratorUrl = insultGeneratorUrl;
    }

    @Override
    public String generateInsult() {
        InsultDTO insult = restTemplate.getForObject(insultGeneratorUrl, InsultDTO.class);
        return insult.getInsult();
    }

    static class InsultDTO {

        private String insult;

        public String getInsult() {
            return insult;
        }

        public void setInsult(String insult) {
            this.insult = insult;
        }
    }
}
