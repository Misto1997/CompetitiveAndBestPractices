package com.f.mcround.dao;

import com.f.mcround.DBSimulator;
import com.f.mcround.model.FeedDetail;
import com.f.mcround.model.Topic;
import com.f.mcround.model.User;
import com.f.mcround.model.FeedAnswer;

import java.util.*;

public class FeedDao {

    private DBSimulator dbSimulator;

    public FeedDao(DBSimulator dbSimulator) {
        this.dbSimulator = dbSimulator;
    }

    public void addQuestion(String question, FeedDetail feedDetail) {
        feedDetail.setFeedAnswers(new ArrayList<>());
        dbSimulator.getFeeds().put(question, feedDetail);
    }

    public void getFeeds() {
        List<String> questionsLists = new ArrayList<>(dbSimulator.getFeeds().keySet());
        System.out.println(questionsLists);
    }

    public void showFeedByAnswer() {
        Map<String, FeedDetail> map = dbSimulator.getFeeds();
        List<String> questionsLists = new ArrayList<>();
        for (Map.Entry<String, FeedDetail> entry : map.entrySet()) {
            if (entry.getValue().getFeedAnswers() != null)
                questionsLists.add(entry.getKey());
        }
        System.out.println(questionsLists);
    }

    public void showFeedByUnAnswered() {
        Map<String, FeedDetail> map = dbSimulator.getFeeds();
        List<String> questionsLists = new ArrayList<>();
        for (Map.Entry<String, FeedDetail> entry : map.entrySet()) {
            if (entry.getValue().getFeedAnswers().isEmpty())
                questionsLists.add(entry.getKey());
        }
        System.out.println(questionsLists);
    }

    public void showFeedByTopic(Set<Topic> topics) {
        Map<String, FeedDetail> map = dbSimulator.getFeeds();
        Set<String> questionsLists = new HashSet<>();
        for (Map.Entry<String, FeedDetail> entry : map.entrySet()) {
            Set<Topic> topicList = entry.getValue().getTopicsAttached();
            if (topicList != null) {
                for (Topic topic : topics) {
                    if (topicList.contains(topic))
                        questionsLists.add(entry.getKey());
                }
            }
        }
        System.out.println(questionsLists);
    }

    public FeedDetail getFeedByQuestion(String question) {
        return dbSimulator.getFeeds().get(question);
    }

    public boolean addAnswer(String currentUser, String question, String answer) {
        User user = dbSimulator.getUsers().get(currentUser);
        FeedDetail feedDetail = dbSimulator.getFeeds().get(question);
        if (user == null || feedDetail == null)
            return false;
        Set<Topic> topicList = user.getSubscriptionList();
        boolean canAnswer = false;
        for (Topic topic : topicList) {
            if (feedDetail.getTopicsAttached().contains(topic)) {
                canAnswer = true;
                break;
            }
        }
        if (canAnswer) {
            List<FeedAnswer> list = feedDetail.getFeedAnswers();
            FeedAnswer feedAnswer = new FeedAnswer();
            feedAnswer.setAnswer(answer);
            list.add(feedAnswer);
            System.out.println(dbSimulator.getFeeds().toString());
            return true;
        }
        return false;
    }
}