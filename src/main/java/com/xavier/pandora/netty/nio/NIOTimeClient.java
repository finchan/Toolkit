package com.xavier.pandora.netty.nio;

public class NIOTimeClient {
    public static void main(String[] args) {
        int port = 8080;
        new Thread(
                new NIOTimeClientHandle("127.0.0.1", port),
                "TimeClient-001")
                .start();;
    }
}
