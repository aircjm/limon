package com.aircjm.project.card.service;

import com.aircjm.project.card.domain.TrelloCard;
import com.aircjm.project.card.domain.Webhook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.julienvey.trello.Trello;

public interface TrelloCardService extends IService<TrelloCard> {


    public Webhook createWebhook(String description, String callbackUrl, String idModel);

    public void deleteWebhook(String idWebhook);

    public Webhook getWebhook(String idWebhook);
}
