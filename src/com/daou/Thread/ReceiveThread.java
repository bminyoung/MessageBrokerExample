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
                if(receive == null){
                    System.out.println("연결이 끊겼습니다");
                    break;
                }else{
                    System.out.println("서버로부터의 메시지: " + receive);
                    publisher.publish(receive);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
