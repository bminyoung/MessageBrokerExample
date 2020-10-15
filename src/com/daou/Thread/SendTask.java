package com.daou.Thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendTask implements Runnable {
    private Socket socket;
    private String msg;

    public SendTask(Socket socket, String msg){
        this.socket = socket;
        this.msg = msg;
    }

    @Override
    public void run() {
        try{
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(msg);
            printWriter.flush();
        } catch (IOException e) {
            System.out.println("send msg fail");
        }
    }
}
