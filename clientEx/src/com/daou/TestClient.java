package com.daou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

    public static void main(String[] args) {
	    Socket socket;
	    try{
	        socket = new Socket("192.168.35.198", 7777);
        } catch (IOException e) {
            return;
        }

        System.out.println("This is Client, Hi broker");
	    Thread thread = new Thread(() -> {
	        try{
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while(true){
                    String receive = br.readLine();
                    if(receive == null){
                        System.out.println("disconnected");
                        break;
                    }else{
                        System.out.println("message from server: " + receive);
                    }
                }
                br.close();
            } catch (IOException e) {
            }
        });
	    thread.start();
    }
}
