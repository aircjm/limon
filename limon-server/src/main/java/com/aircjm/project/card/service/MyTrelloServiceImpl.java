package com.aircjm.project.card.service;

import com.aircjm.project.card.domain.Webhook;
import org.springframework.stereotype.Service;

@Service
public class MyTrelloServiceImpl implements MyTrelloService {

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
