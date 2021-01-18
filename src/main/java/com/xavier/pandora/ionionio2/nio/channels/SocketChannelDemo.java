package com.xavier.pandora.ionionio2.nio.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static final Logger logger = LoggerFactory.getLogger(SocketChannelDemo.class);

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        InetSocketAddress addr = new InetSocketAddress("localhost", 9999);
        sc.connect(addr);
        while(!sc.finishConnect()) {
            logger.info("waiting to finish connection");
        }
        ByteBuffer buffer = ByteBuffer.allocate(200);
        String content ="";
        //==0 mean's null(empty) char, if you omit this, you can not get data!!!
        while(sc.read(buffer) >= 0) {
            buffer.flip();
            while(buffer.hasRemaining()) {
                content += (char) buffer.get();
            }
            buffer.clear();
        }
        logger.info(content);
        sc.close();
    }
}
