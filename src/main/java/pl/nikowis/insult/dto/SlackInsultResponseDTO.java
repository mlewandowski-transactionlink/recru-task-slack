package pl.nikowis.insult.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlackInsultResponseDTO {

    private String responseType = "in_channel";
    private String text;

    public SlackInsultResponseDTO(String insult) {
        this.text = insult;
    }

    @JsonProperty("response_type")
    public String getResponseType() {
        return responseType;
    }

    public String getText() {
        return text;
    }
}
