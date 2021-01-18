package com.xavier.pandora.ionionio2.nio.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class DatagramChannelClient {
    public static final Logger logger = LoggerFactory.getLogger(DatagramChannelClient.class);

    public static void main(String[] args) throws IOException {
        DatagramChannel dcClient = DatagramChannel.open();
    }
}
