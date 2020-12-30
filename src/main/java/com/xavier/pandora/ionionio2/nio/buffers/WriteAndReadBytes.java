package com.xavier.pandora.ionionio2.nio.buffers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class WriteAndReadBytes {
    public static final Logger log = LoggerFactory.getLogger(WriteAndReadBytes.class);

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        log.info("Capacity = " + buffer.capacity());
        log.info("Position = " + buffer.position());
        log.info("Limit = " + buffer.limit());
        log.info("Remaining = " + buffer.remaining());

        buffer.put((byte)10).put((byte)20).put((byte)30);
        log.info("Capacity = " + buffer.capacity());
        log.info("Position = " + buffer.position());
        log.info("Limit = " + buffer.limit());
        log.info("Remaining = " + buffer.remaining());

        for (int i = 0; i < buffer.position(); i++) {
            log.info(String.valueOf(buffer.get(i)));
        }
    }
}
