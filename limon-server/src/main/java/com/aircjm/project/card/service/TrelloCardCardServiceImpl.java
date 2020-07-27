package com.aircjm.project.card.service;

import com.aircjm.project.card.domain.TrelloCard;
import com.aircjm.project.card.domain.Webhook;
import com.aircjm.project.card.mapper.TrelloCardMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TrelloCardCardServiceImpl extends ServiceImpl<TrelloCardMapper, TrelloCard> implements TrelloCardService {

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
