package com.daou.messageBroker;

import com.daou.Thread.ReceiveTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

public class MyMessageBroker extends Subject{
    //manage in broker
    private static final HashMap<String, HashSet<Subscriber>> subscribers = new HashMap<>();
    private Publisher publisher;
    private ServerSocket brokerServerSocket;

    /*
    * use table for pub/sub!
    * */

    MyMessageBroker(){
        subscribers.put("/topic", new HashSet<>());
    }

    public HashMap<String, HashSet<Subscriber>> getSubscribers(){
        return subscribers;
    }

    /*
    * connect to server
    * */
    public void useBroker(int port) {
        try {

            brokerServerSocket = new ServerSocket();
            InetSocketAddress endpoint = new InetSocketAddress(port);
            brokerServerSocket.bind(endpoint);

            /* brokerServerSocket.listen */

            while(true){
                Socket acceptSocket = brokerServerSocket.accept();
                System.out.println("client has been connected");

                //tmp: auto sub
                subscribers.get("/topic").add(new Subscriber(acceptSocket));

                ReceiveTask receiveTask = new ReceiveTask(new Publisher(acceptSocket));
                Thread receiveThread = new Thread(receiveTask);
                receiveThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void subscribe(String topic, Socket socket){
        if(publisher == null) return;

        Subscriber sub = new Subscriber(socket);
        publisher.subscribe(sub);
    }

    @Override
    void notifyObservers(String msg) {

    }

    /*
    * accept subscribers
    * */
//    private void acceptSubscriber() {
//        try {
//            if(publisher == null) return;
//
//            while (true) {
//                //2. broker have to manage subs by topic
//                Socket acceptSocket = brokerServerSocket.accept();
//                String topic = "";
//                subscribe(topic, acceptSocket);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
