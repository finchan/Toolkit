package com.xavier.pandora.ionionio2.nio.buffers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class ViewBufferDemo {
    public static final Logger log = LoggerFactory.getLogger(ViewBufferDemo.class);

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(6);
        byte zero = 0;
        bb.put(zero).put((byte)0x6e).put(zero).put((byte)0x69)
                .put(zero).put((byte)0x6f);
        bb.rewind();
        //ViewBuffer
        CharBuffer cb = bb.asCharBuffer();
        for (int i = 0; i < cb.limit(); i++) {
            log.info(String.valueOf(cb.get()));
        }
    }
}
