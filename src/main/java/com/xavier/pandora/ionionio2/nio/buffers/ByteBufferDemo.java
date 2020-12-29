package com.xavier.pandora.ionionio2.nio.buffers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class ByteBufferDemo {
    public static final Logger log = LoggerFactory.getLogger(ByteBufferDemo.class);

    public static void main(String[] args) {
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        if(buffer1.hasArray()) {
            log.info("buffer1 array: " + buffer1.array());
            log.info("Buffer1 array offset: " + buffer1.arrayOffset());
            log.info("Capacity: " + buffer1.capacity());
            log.info("Limit: " + buffer1.limit());
            log.info("Position: " + buffer1.position());
            log.info("Remaining: " + buffer1.remaining());
            log.info("=======================");
        }

        byte[] bytes = new byte[200];
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(bytes);
        byteBuffer2 = ByteBuffer.wrap(bytes, 10, 50);
        if(byteBuffer2.hasArray()) {
            log.info("byteBuffer2 array: " + byteBuffer2.array());
            log.info("byteBuffer2 array offset: " + byteBuffer2.arrayOffset());
            log.info("Capacity: " + byteBuffer2.capacity());
            log.info("Limit: " + byteBuffer2.limit());
            log.info("Position: " + byteBuffer2.position());
            log.info("Remaining: " + byteBuffer2.remaining());
            log.info("=======================");
        }
    }
}
