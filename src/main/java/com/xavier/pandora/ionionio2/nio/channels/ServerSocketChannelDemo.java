package com.xavier.pandora.ionionio2.nio.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {
    public static final Logger logger = LoggerFactory.getLogger(ServerSocketChannelDemo.class);

    public static void main(String[] args) throws IOException {
        System.out.println("Starting server...");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(9999));
        ssc.configureBlocking(false);
        String msg = "local address: " + ssc.socket().getLocalSocketAddress();
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        while(true) {
            System.out.print(".");
            SocketChannel sc = ssc.accept();
            if(sc!=null) {
                System.out.println();
                logger.info("Received connection from " + sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            } else
                try{
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    assert false;
                }
        }
    }
}
