package com.daou.messageBroker;

import com.daou.Thread.SendTask;

import java.net.Socket;

public class Subscriber implements Observer{

    private final Socket socket;

    Subscriber(Socket socket){
        this.socket = socket;
    }

    @Override
    public void send(String msg) {
        Thread thread = new Thread(new SendTask(socket, msg));
        thread.start();
    }
}
