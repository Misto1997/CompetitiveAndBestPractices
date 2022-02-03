package com.f.mcround.service;

import com.f.mcround.FeedFilterType;
import com.f.mcround.model.FeedDetail;
import com.f.mcround.model.Topic;
import com.f.mcround.dao.FeedDao;

import java.util.Set;

public class FeedService {
    FeedDao feedDao;

    public FeedService(FeedDao feedDao) {
        this.feedDao = feedDao;
    }

    public boolean addQuestion(String question, FeedDetail feedDetail) {
        if (feedDetail.getTopicsAttached() == null)
            return false;
        feedDao.addQuestion(question, feedDetail);
        return true;
    }

    public void showFeeds() {
        feedDao.getFeeds();
    }

    //Map can be used here to replace if else
    public void showFeedByFilter(FeedFilterType filterType, Set<Topic> list) {
        if (filterType == FeedFilterType.ANSWERED) {
            feedDao.showFeedByAnswer();
        } else if (filterType == FeedFilterType.UNANSWERED) {
            feedDao.showFeedByUnAnswered();
        } else {
            feedDao.showFeedByTopic(list);
        }
    }

    public String showQuestion(String question) {
        FeedDetail feedDetail = feedDao.getFeedByQuestion(question);
        if (feedDetail == null)
            return "No Feed available";
        return feedDetail.toString();

    }

    public String addAnswer(String currentUser, String question, String answer) {
        boolean isValid = feedDao.addAnswer(currentUser, question, answer);
        if (isValid)
            return "your answer is added";
        return "you cannot answer this question;";

    }
}
