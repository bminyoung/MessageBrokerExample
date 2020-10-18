package com.daou.messageBroker;

import java.net.Socket;

public class Publisher extends Subject{

    private final Socket socket;
    private String topic;

    Publisher(Socket socket){
        this.socket = socket;
    }

    public void publish(String topic, String msg){
        notifyObservers(topic, msg);
    }

    public Socket getSocket() {
        return socket;
    }
}
