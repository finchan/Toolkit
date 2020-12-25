package com.xavier.pandora.netty.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
    public static  final Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while(true) {
                body = in.readLine();
                if(body == null)
                    break;
                logger.info("The time server receive order: " + body);
                switch (body) {
                    case "TIME":
                        currentTime = new Date(System.currentTimeMillis()).toString();
                        Thread.sleep(30000);
                        break;
                    case "YEAR":
                        currentTime = String.valueOf(new Date(System.currentTimeMillis()).getYear());
                        Thread.sleep(20000);
                        break;
                    case "MONTH":
                        currentTime = String.valueOf(new Date(System.currentTimeMillis()).getMonth());
                        Thread.sleep(10000);
                        break;
                    default:
                        currentTime = "BAD TIME";
                }
                logger.info(currentTime);
                out.println(currentTime);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null) {
                out.close();
                out = null;
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                    this.socket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
