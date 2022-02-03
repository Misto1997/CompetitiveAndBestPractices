package com.f.mcround.dao;

import com.f.mcround.DBSimulator;
import com.f.mcround.model.Topic;
import com.f.mcround.model.User;

import java.util.Set;

public class SubscriptionDao {

    private DBSimulator dbSimulator;

    public SubscriptionDao(DBSimulator dbSimulator) {
        this.dbSimulator = dbSimulator;
    }

    public void subscribeToTopic(String username, Set<Topic> topics) {
        User user = dbSimulator.getUsers().get(username);
        user.getSubscriptionList().addAll(topics);
    }

    public void unsubscribeToTopic(String username, Set<Topic> topics) {
        User user = dbSimulator.getUsers().get(username);
        Set<Topic> topicList = user.getSubscriptionList();
        for (Topic topic : topics) {
            topicList.remove(topic);
        }
    }
}
