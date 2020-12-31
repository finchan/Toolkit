package com.xavier.pandora.ionionio2.nio.buffers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class BufferCompact {
    public static final Logger log = LoggerFactory.getLogger(BufferCompact.class);

    public static void main(String[] args) throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        BufferCompact.copy(source, dest);
        source.close();
        dest.close();
    }

    public static void copy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer tmp = ByteBuffer.allocate(10*1024);
        while(src.read(tmp) != -1){
            tmp.flip();
            dest.write(tmp);
            tmp.compact(); //Do this in case of partial write
        }
        tmp.flip();
        while(tmp.hasRemaining()) {
            dest.write(tmp);
        }
    }
}
