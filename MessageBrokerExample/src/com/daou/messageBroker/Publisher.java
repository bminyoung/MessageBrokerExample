package com.daou.messageBroker;

import java.net.Socket;

public class Publisher extends Subject{

    private final Socket socket;
    private String topic;

    Publisher(Socket socket){
        this.socket = socket;
    }

    public void publish(String msg){
        notifyObservers(msg);
    }

    public Socket getSocket() {
        return socket;
    }
}
