package com.xavier.pandora.ionionio2.nio.buffers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;


public class BufferDemo {
    public static final Logger log = LoggerFactory.getLogger(BufferDemo.class);
    public static void main(String[] args) {
        Buffer buffer = ByteBuffer.allocate(7);
        log.info("Capacity: " + buffer.capacity());
        log.info("Limit: " + buffer.limit());
        log.info("Position: " + buffer.position());
        log.info("Remaining: " + buffer.remaining());
        log.info("Change buffer limit to 5");
        buffer.limit(5);
        log.info("Capacity: " + buffer.capacity());
        log.info("Limit: " + buffer.limit());
        log.info("Position: " + buffer.position());
        log.info("Remaining: " + buffer.remaining());
        log.info("Change buffer position to 3");
        buffer.position(3);
        log.info("Capacity: " + buffer.capacity());
        log.info("Limit: " + buffer.limit());
        log.info("Position: " + buffer.position());
        log.info("Remaining: " + buffer.remaining());
        log.info(buffer.toString());
    }
}
