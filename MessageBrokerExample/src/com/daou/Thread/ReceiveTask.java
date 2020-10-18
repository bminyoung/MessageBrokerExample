package com.daou.Thread;

import com.daou.messageBroker.Publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReceiveTask implements Runnable {
    private Publisher publisher;

    public ReceiveTask(Publisher publisher){
        this.publisher = publisher;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(publisher.getSocket().getInputStream()));
            while(true){
                String topic = br.readLine();
                String receive = br.readLine();
                System.out.println("publish msg: " + receive);
                publisher.publish(topic, receive);
            }
        } catch (IOException e) {
            System.out.println("disconnected");
            publisher.close();
        }
    }
}
