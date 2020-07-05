package com.aircjm.project.card.domain;

import lombok.Data;

@Data
public class Webhook {

    private String description;
    private String callbackURL;
    private String idModel;
    private Boolean active;
}