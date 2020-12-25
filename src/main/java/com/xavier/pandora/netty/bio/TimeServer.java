package com.xavier.pandora.netty.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static final Logger logger = LoggerFactory.getLogger(TimeServer.class);
    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            logger.info("The time server is start in port: " + port);
            Socket socket = null;
            while(true) {
                socket = serverSocket.accept();
                logger.info(String.valueOf(System.identityHashCode(socket)));
                new Thread(new TimeServerHandler((socket))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null) {
                try {
                    logger.info("The time server close");
                    serverSocket.close();
                    serverSocket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
            }
        }
    }
}
