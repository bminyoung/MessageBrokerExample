package com.daou;

import com.daou.messageBroker.MyMessageBroker;

public class Main {

    public static void main(String[] args) {
        try{
            int port = Integer.parseInt(args[0]);
            MyMessageBroker messageBroker = new MyMessageBroker();
            messageBroker.useBroker(port);
        }catch(NumberFormatException e){
            System.out.println("port must be integer");
        }
    }
}
