package com.daou.messageBroker;

import java.net.Socket;

public class Publisher extends Subject{

    private final Socket socket;

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
