package com.aircjm.project.card.service;

import com.aircjm.project.card.domain.Webhook;
import com.julienvey.trello.Trello;

public interface MyTrelloService {


    public Webhook createWebhook(String description, String callbackUrl, String idModel);

    public void deleteWebhook(String idWebhook);

    public Webhook getWebhook(String idWebhook);
}
