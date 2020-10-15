package com.daou.messageBroker;

import com.daou.Thread.ReceiveThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyMessageBroker {
    private Publisher publisher;
    private ServerSocket brokerServerSocket;

    public void useBroker(int port) {
        try {
            brokerServerSocket = new ServerSocket(port);

            //connect to server
            Socket serverSocket = brokerServerSocket.accept();
            publisher = new Publisher(serverSocket);

            ReceiveThread receiveThread = new ReceiveThread(publisher);
            receiveThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        acceptSubscriber();
    }

    private void acceptSubscriber() {
        try {
            while (true) {
                Socket acceptSocket = brokerServerSocket.accept();
                //no topic req
                subscribe(acceptSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void subscribe(Socket socket){
        Subscriber sub = new Subscriber(socket);
        publisher.subscribe(sub);
    }
}
