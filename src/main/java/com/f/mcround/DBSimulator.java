package com.f.mcround;

import com.f.mcround.model.FeedDetail;
import com.f.mcround.model.Topic;
import com.f.mcround.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBSimulator {

    List<Topic> topics;
    Map<String, User> users;
    Map<String, FeedDetail> feeds;


    private DBSimulator(List<Topic> topics, Map<String, User> users, Map<String, FeedDetail> feeds) {
        this.topics = topics;
        this.users = users;
        this.feeds = feeds;
    }

    public static class DBSimulatorInstance {
        private static final DBSimulator INSTANCE = new DBSimulator(new ArrayList<>(), new HashMap<>(), new HashMap<>());
    }

    public static DBSimulator getInstance() {
        return DBSimulatorInstance.INSTANCE;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public Map<String, FeedDetail> getFeeds() {
        return feeds;
    }

    public void setFeeds(Map<String, FeedDetail> feeds) {
        this.feeds = feeds;
    }
}
