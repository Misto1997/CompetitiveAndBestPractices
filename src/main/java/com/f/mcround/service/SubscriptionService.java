package com.f.mcround.service;

import com.f.mcround.dao.SubscriptionDao;
import com.f.mcround.model.Topic;

import java.util.Set;

public class SubscriptionService {

    SubscriptionDao subscriptionDao;

    public SubscriptionService(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    public void subscribeUserToTopics(String username, Set<Topic> topics) {
        subscriptionDao.subscribeToTopic(username, topics);
    }

    public void unSubscribeUserToTopic(String username, Set<Topic> topics) {
        subscriptionDao.unsubscribeToTopic(username, topics);
    }
}
