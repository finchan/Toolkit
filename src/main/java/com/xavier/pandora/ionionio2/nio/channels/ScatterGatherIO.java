package com.xavier.pandora.ionionio2.nio.channels;

import com.xavier.pandora.basic.IOUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

public class ScatterGatherIO {
    public static final Logger log = LoggerFactory.getLogger(ScatterGatherIO.class);

    public static void main(String[] args) throws IOException {
        ScatteringByteChannel src;
        GatheringByteChannel dest;
        FileInputStream fis = new FileInputStream(IOUtility.getResourcePath("/samples/nio/x.dat"));
        src = (ScatteringByteChannel) Channels.newChannel(fis);
        ByteBuffer buffer1 = ByteBuffer.allocate(5);
        ByteBuffer buffer2 = ByteBuffer.allocate(3);
        ByteBuffer[] buffers = {buffer1, buffer2};
        src.read(buffers);
        buffer1.flip();
        while(buffer1.hasRemaining()) {
            log.info("Buffer1: " + buffer1.get());
        }
        buffer2.flip();
        while(buffer2.hasRemaining()) {
            log.info("Buffer2: " + buffer2.get());
        }
        buffer1.rewind();
        buffer2.rewind();
        buffers[0] = buffer2;
        buffers[1] = buffer1;
        FileOutputStream fos = new FileOutputStream(IOUtility.createResourcePath("/resources/samples/nio/y.dat"));
        dest = (GatheringByteChannel) Channels.newChannel(fos);
        dest.write(buffers);
    }
}
