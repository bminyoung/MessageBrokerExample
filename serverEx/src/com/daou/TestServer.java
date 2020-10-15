package com.daou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestServer {

    public static void main(String[] args) {
	    Socket socket;
	    try{
	        socket = new Socket("192.168.35.198", 7777);
        } catch (IOException e) {
            return;
        }

        System.out.println("Connection with broker");
	    Thread thread = new Thread(() -> {
	        try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                String sendMsg = "";

                while(!sendMsg.equals("exit")){
                    sendMsg = br.readLine();
                    printWriter.println(sendMsg);
                    printWriter.flush();
                }
            } catch (IOException e) {
                System.out.println("disconnect with broker");
            }
        });
	    thread.start();
    }
}
