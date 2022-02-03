package com.f.mcround.model;

import java.util.List;
import java.util.Set;

public class FeedDetail {

    Set<Topic> topicsAttached;
    List<FeedAnswer> feedAnswers;
    Boolean isAccepted;
    Integer votes;
    User user;


    public Set<Topic> getTopicsAttached() {
        return topicsAttached;
    }

    public void setTopicsAttached(Set<Topic> topicsAttached) {
        this.topicsAttached = topicsAttached;
    }

    public List<FeedAnswer> getFeedAnswers() {
        return feedAnswers;
    }

    public void setFeedAnswers(List<FeedAnswer> feedAnswers) {
        this.feedAnswers = feedAnswers;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FeedDetail{" +
                "topicsAttached=" + topicsAttached +
                ", feedAnswers=" + feedAnswers +
                ", isAccepted=" + isAccepted +
                ", votes=" + votes +
                '}';
    }
}
