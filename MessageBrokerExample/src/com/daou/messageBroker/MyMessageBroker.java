package com.daou.messageBroker;

import com.daou.Thread.ReceiveTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MyMessageBroker {
    private HashMap<String, Publisher> publishers = new HashMap<>();
    private Publisher publisher;
    private ServerSocket brokerServerSocket;

    /*
    * connect to server
    * */
    public void useBroker(int port) {
        try {
            brokerServerSocket = new ServerSocket();
            InetSocketAddress endpoint = new InetSocketAddress(port);
            brokerServerSocket.bind(endpoint);

            /* brokerServerSocket.listen */

            //1. how can broker know if it's server -> register publisher
            Socket serverSocket = brokerServerSocket.accept();
            publisher = new Publisher(serverSocket);
//            String topic = "";
//            publishers.put(topic, publisher); //if null
            System.out.println("server has been connected");

            ReceiveTask receiveTask = new ReceiveTask(publisher);
            Thread receiveThread = new Thread(receiveTask);
            receiveThread.start();

            acceptSubscriber();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * accept subscribers
    * */
    private void acceptSubscriber() {
        try {
            if(publisher == null) return;

            while (true) {
                //2. broker have to manage subs by topic
                Socket acceptSocket = brokerServerSocket.accept();
                subscribe(acceptSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void subscribe(Socket socket){
        if(publisher == null) return;

        Subscriber sub = new Subscriber(socket);
        publisher.subscribe(sub);
    }
}
