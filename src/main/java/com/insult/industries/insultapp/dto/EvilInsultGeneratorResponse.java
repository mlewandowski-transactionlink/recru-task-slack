package com.insult.industries.insultapp.dto;

import lombok.Data;

@Data
public class EvilInsultGeneratorResponse {
    String number;
    String language;
    String insult;
    String created;
    String shown;
    String createdby;
    String active;
    String comment;
}
