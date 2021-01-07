package pl.nikowis.insult.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlackBotResponseDTO {

    private Visibility visibility;
    private String text;

    public SlackBotResponseDTO(String insult) {
        this.text = insult;
        this.visibility = Visibility.PUBLIC;
    }

    public SlackBotResponseDTO(String message, Visibility visibility) {
        this.text = message;
        this.visibility = visibility;
    }

    @JsonProperty("response_type")
    public String getResponseType() {
        return visibility.getValue();
    }

    public String getText() {
        return text;
    }

    public enum Visibility {
        PRIVATE("ephemeral"), PUBLIC("in_channel");

        String value;

        Visibility(String val) {
            this.value = val;
        }

        public String getValue() {
            return value;
        }
    }
}
