package com.daou.messageBroker;

import com.daou.Thread.SendTask;

import java.net.Socket;
import java.util.HashSet;

public class Subscriber implements Observer{

    private final Socket socket;

    Subscriber(Socket socket){
        this.socket = socket;
    }

    @Override
    public void send(String msg) {
        if(isContainTopic("/topic")){
            Thread thread = new Thread(new SendTask(socket, msg));
            thread.start();
        }
    }

    public boolean isContainTopic(String topic){
        return topicSet.contains(topic);
    }
}
