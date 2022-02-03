package com.f.mcround;

import com.f.mcround.dao.FeedDao;
import com.f.mcround.dao.SubscriptionDao;
import com.f.mcround.dao.UserDao;
import com.f.mcround.model.FeedDetail;
import com.f.mcround.model.Topic;
import com.f.mcround.model.User;
import com.f.mcround.service.FeedService;
import com.f.mcround.service.SubscriptionService;
import com.f.mcround.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class Driver {

    static String currentUser;

    public static void main(String[] args) {
        DBSimulator dbSimulator = DBSimulator.getInstance();

        UserService userService = new UserService(new UserDao(dbSimulator));
        User user = new User();
        user.setAge(5);
        user.setUserName("misto");
        user.setProfession("Dev");
        currentUser = userService.registration(user);

        SubscriptionService subscriptionService = new SubscriptionService(new SubscriptionDao(dbSimulator));
        Set<Topic> topicList = new HashSet<>();
        Topic topic = new Topic();
        topic.setTopicName("java");
        topicList.add(topic);
        Topic topic1 = new Topic();
        topic1.setTopicName("python");
        topicList.add(topic1);
        subscriptionService.subscribeUserToTopics(currentUser, topicList);

        Set<Topic> unsubscribeList = new HashSet<>();
        Topic topic2 = new Topic();
        topic2.setTopicName("java");
        unsubscribeList.add(topic2);
        subscriptionService.unSubscribeUserToTopic(currentUser, unsubscribeList);


        FeedService feedService = new FeedService(new FeedDao(dbSimulator));
        FeedDetail feedDetail = new FeedDetail();
        feedDetail.setTopicsAttached(topicList);
        feedDetail.setUser(user);
        boolean isAdded = feedService.addQuestion("what is java", feedDetail);
        if (!isAdded)
            System.out.println("topic needs to be attached");
        feedService.showFeeds();



        System.out.println(feedService.showQuestion("what is java"));


        User newUser1 = new User();
        newUser1.setUserName("Sachin");
        newUser1.setProfession("Dev");
        currentUser = userService.registration(newUser1);
        feedService.showFeeds();
        subscriptionService.subscribeUserToTopics(currentUser,topicList);
        System.out.println(feedService.addAnswer(currentUser, "what is java", "it is cool"));
        feedService.showFeeds();
        subscriptionService.unSubscribeUserToTopic(currentUser,topicList);
        System.out.println(feedService.addAnswer(currentUser, "what is java", "it is cool"));
        feedService.showFeeds();
        System.out.println(feedService.showQuestion("what is java"));

        System.out.println("-------");
        feedService.showFeedByFilter(FeedFilterType.ANSWERED, new HashSet<>());
        System.out.println("-------");
        feedService.showFeedByFilter(FeedFilterType.UNANSWERED, new HashSet<>());
        System.out.println("--------");
        feedService.showFeedByFilter(FeedFilterType.TOPIC_NAME, topicList);
        Set<Topic> newTopicList=new HashSet<>();
        Topic topic3=new Topic();
        topic3.setTopicName("golang");
        FeedDetail feedDetail1=new FeedDetail();
        feedDetail1.setTopicsAttached(newTopicList);
        feedService.addQuestion("what is golang", feedDetail1);
        feedService.showFeeds();

        System.out.println("%%%%");
        feedService.showFeedByFilter(FeedFilterType.UNANSWERED, new HashSet<>());
        System.out.println("%%%");
        feedService.showFeedByFilter(FeedFilterType.TOPIC_NAME, topicList);


    }
}
