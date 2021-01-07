package pl.nikowis.insult.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nikowis.insult.dto.SlackBotResponseDTO;
import pl.nikowis.insult.service.InsultingGenerator;
import pl.nikowis.insult.service.InsultingService;

@Service
class InsultingServiceImpl implements InsultingService {

    @Autowired
    private InsultingGenerator insultingGenerator;

    @Override
    public SlackBotResponseDTO getInsultResponse() {
        String insult = insultingGenerator.generateInsult();
        return new SlackBotResponseDTO(insult);
    }
}
