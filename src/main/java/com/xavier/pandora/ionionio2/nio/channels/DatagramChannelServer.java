package com.xavier.pandora.ionionio2.nio.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelServer {
    public static final Logger logger = LoggerFactory.getLogger(DatagramChannelServer.class);
    final static int PORT = 9999;
    public static void main(String[] args) throws IOException {
        logger.info("Server starting and listening on prot " + PORT + " for incoming requests...");
        DatagramChannel dcServer = DatagramChannel.open();
        dcServer.socket().bind(new InetSocketAddress(PORT));
        ByteBuffer symbol = ByteBuffer.allocate(4);
        ByteBuffer payload = ByteBuffer.allocate(16);
        while(true) {
            payload.clear();
            symbol.clear();
            SocketAddress sa = dcServer.receive(symbol);
            if (sa == null)
                return;
            logger.info("Received request from " + sa);
            String stockSymbol = new String(symbol.array(), 0, 4);
            logger.info("Symbol - " + stockSymbol);
            if("MSFT".equals(stockSymbol.toUpperCase())){
                payload.putFloat(0, 37.40f);
                payload.putFloat(4, 37.22f);
                payload.putFloat(8, 37.48f);
                payload.putFloat(12,37.41f);
            } else {
                payload.putFloat(0, 0.0f);
                payload.putFloat(4, 0.0f);
                payload.putFloat(8, 0.0f);
                payload.putFloat(12,0.0f);
            }
            dcServer.send(payload, sa);
        }
    }
}
