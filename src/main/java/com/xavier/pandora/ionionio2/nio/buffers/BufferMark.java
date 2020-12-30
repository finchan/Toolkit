package com.xavier.pandora.ionionio2.nio.buffers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class BufferMark {
    public static final Logger log = LoggerFactory.getLogger(BufferMark.class);

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.put((byte)10).put((byte)20).put((byte)30).put((byte)40);
        buffer.limit(4);
        buffer.position(1).mark().position(3);
        log.info(String.valueOf(buffer.get()));
        log.info("===");
        buffer.reset();
        while(buffer.hasRemaining()) {
            log.info(String.valueOf(buffer.get()));
        }
    }
}
