package com.daou.Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendThread extends Thread{
    private Socket socket;

    public SendThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        super.run();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            String sendMsg = "";

            while(!sendMsg.equals("exit")){
                sendMsg = br.readLine();
                printWriter.println(sendMsg);
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
