package com.xavier.pandora.ionionio2.nio.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;


public class InputChannelOutputChannel {
    public static final Logger log = LoggerFactory.getLogger(InputChannelOutputChannel.class);

    public static void main(String[] args) {
        ReadableByteChannel src = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        try{
            copy(src, dest);
//            altCopy(src, dest);
        } catch(IOException ioe) {
            log.info(ioe.toString());
        } finally {
            try {
                src.close();
                dest.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void altCopy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        while(src.read(buffer) != -1) {
            buffer.flip();
            while(buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear();
        }
    }

    private static void copy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        while(src.read(buffer) != -1) {
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while(buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }
}
