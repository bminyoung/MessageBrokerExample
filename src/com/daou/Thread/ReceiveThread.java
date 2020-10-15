package com.daou.Thread;

import com.daou.messageBroker.Publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReceiveThread extends Thread{
    private Publisher publisher;

    public ReceiveThread(Publisher publisher){
        this.publisher = publisher;
    }

    @Override
    public void run() {
        super.run();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(publisher.getSocket().getInputStream()));
            while(true){
                String receive = br.readLine();
                System.out.println("publish msg: " + receive);
                publisher.publish(receive);
            }
        } catch (IOException e) {
            System.out.println("disconnected");
            publisher.close();
        }
    }
}
