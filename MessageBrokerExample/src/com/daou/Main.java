package com.daou;

import com.daou.messageBroker.MyMessageBroker;

public class Main {
    private static final int PORT = 7777;

    public static void main(String[] args) {
        System.out.println("This is broker");
        try{
            MyMessageBroker messageBroker = new MyMessageBroker();
            messageBroker.useBroker(PORT);
        }catch(NumberFormatException e){
            System.out.println("port must be integer");
        }
    }
}
