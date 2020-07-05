package com.aircjm.project.card.service;

import com.aircjm.project.card.domain.Webhook;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.impl.TrelloImpl;

public class MyTrelloServiceImpl extends TrelloImpl implements MyTrelloService {

    public MyTrelloServiceImpl(String applicationKey, String accessToken, TrelloHttpClient httpClient) {
        super(applicationKey, accessToken, httpClient);
    }

    @Override
    public Webhook createWebhook(String description, String callbackUrl, String idModel) {
        return null;
    }

    @Override
    public void deleteWebhook(String idWebhook) {

    }

    @Override
    public Webhook getWebhook(String idWebhook) {
        return null;
    }
}
