package com.xavier.pandora.ionionio2.nio.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static final Logger log = LoggerFactory.getLogger(FileChannelDemo.class);

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("temp", "rw");
        FileChannel fc = raf.getChannel();
        long pos;
        log.info("Position: " + (pos = fc.position()));
        //if file is empty, then when created - position 0 size 0
        //!!!if file already exists and the content is not empty - position 0 size: file size
        log.info("Size: " + fc.size());
        String msg = "This is a test message.";
        ByteBuffer buffer = ByteBuffer.allocateDirect(msg.length()*2);
        log.info(buffer.toString());
        //ViewBuffer and buffer have their own position, limit and mark.
        //When you put/get in ViewBuffer, this behavior doesn't change the buffer's position, limit and mark
        buffer.asCharBuffer().put(msg);
        log.info(buffer.toString());
        fc.write(buffer);
        log.info("fc.write() - " + buffer.toString());
        fc.force(true);
        log.info("fc.force(true) - " + buffer.toString());
        log.info("Position: " + fc.position());
        log.info("Size: " + fc.size());
        buffer.clear();
        File file = new File("temp");
        FileInputStream fis = new FileInputStream(file);
        log.info(String.valueOf(file.length()));
        FileChannel fc2 = fis.getChannel();
        log.info("fc2 position: " + fc2.position());
        log.info("fc2 size: " + fc2.size());
        log.info("Done");
        log.info("buffer.clear() - " + buffer.toString());
        fc.position(pos);
        fc.read(buffer);
        log.info("fc.read(buffer) -" + buffer.toString());
        buffer.flip();
        log.info("buffer.flip() - " + buffer.toString());
        while(buffer.hasRemaining()) {
            log.info(String.valueOf(buffer.getChar()));
        }
        log.info("buffer.getChar() - "+buffer.toString());
    }
}
