package com.mergeorder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 17-5-4.
 *
 * topic list
 */
public class TopicList implements Serializable {

    private List<Topic> topicList;

    public TopicList() {
        topicList = new ArrayList<>();
    }

    public TopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public void addTopic(Topic topic) {
        topicList.add(topic);
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @Override
    public String toString() {
        return "TopicList{" +
                "topicList=" + topicList +
                '}';
    }
}
