package com.xavier.pandora.ionionio2.nio.buffers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.CharBuffer;

public class BufferFlip {
    public static final Logger log = LoggerFactory.getLogger(BufferFlip.class);

    public static void main(String[] args) {
        String[] poem = {
                "Roses are red",
                "Violets are blue",
                "Sugar is sweet",
                "And so are you."
        };
        CharBuffer charBuffer = CharBuffer.allocate(50);
        for (int i = 0; i < poem.length; i++) {
            StringBuilder sb = new StringBuilder("");
            for (int j = 0; j < poem[i].length(); j++) {
                charBuffer.put(poem[i].charAt(j));
            }
            charBuffer.flip();
            while(charBuffer.hasRemaining()) {
                sb.append(charBuffer.get());
            }
                log.info(sb.toString());
            // Empty the buffer to prevent BufferOverflowException
            charBuffer.clear();
            log.info("---");
        }
    }
}
