package com.daou.messageBroker;

import java.util.HashSet;

public interface Observer {
    HashSet<String> topicSet = new HashSet<>();
    void send(String msg);
    boolean isContainTopic(String topic);
}
