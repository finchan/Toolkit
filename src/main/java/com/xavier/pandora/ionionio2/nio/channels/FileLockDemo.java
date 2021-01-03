package com.xavier.pandora.ionionio2.nio.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockDemo {
    public static final Logger log = LoggerFactory.getLogger(FileLockDemo.class);
    final static int MAXQUERIES = 1500000;
    final static int MAXUPDATES = 1500000;
    final static int RECLEN = 16;
    static ByteBuffer buffer = ByteBuffer.allocate(RECLEN);
    static IntBuffer intBuffer = buffer.asIntBuffer();
    static int counter = 1;

    public static void main(String[] args) throws IOException {
        boolean writer = false;
        if(args.length != 0) {
            writer = true;
        }
        RandomAccessFile raf = new RandomAccessFile("temp", writer ? "rw" : "r");
        FileChannel fc = raf.getChannel();
        if(writer) {
            update(fc);
        } else {
            query(fc);
        }
    }

    private static void query(FileChannel fc) throws IOException {
        for (int i = 0; i < MAXQUERIES; i++) {
            log.info("Acquiring shared lock");
            FileLock lock = fc.lock(0, RECLEN, true);
            try{
                buffer.clear();
                fc.read(buffer);
                int a = intBuffer.get(0);
                int b = intBuffer.get(1);
                int c = intBuffer.get(2);
                int d = intBuffer.get(3);
                log.info("Reading: " + a + " " + b + " "+ c + " " + d);
                if(a*2 != b || a*3 != c || a*4 != d) {
                    log.info("Error");
                    return;
                }
            } finally {
                lock.release();
            }
        }
    }

    private static void update(FileChannel fc) throws IOException {
        for (int i = 0; i < MAXUPDATES; i++) {
            log.info("Acquiring exclusive lock");
            FileLock lock = fc.lock(0, RECLEN, false);
            try{
                intBuffer.clear();
                int a = counter;
                int b = counter *2;
                int c = counter *3;
                int d = counter * 4;
                log.info("Writing: " + a + " " + b + " "+ c + " " + d);
                intBuffer.put(a);
                intBuffer.put(b);
                intBuffer.put(c);
                intBuffer.put(d);
                counter++;
                buffer.clear();
                fc.position(i*4);
                fc.write(buffer, 0);
                log.info("fc position: " + fc.position() + " fc size: " + fc.size());
            } finally {
                lock.release();
            }
        }
    }
}
