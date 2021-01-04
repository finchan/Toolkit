package com.xavier.pandora.ionionio2.nio.channels;

import com.xavier.pandora.basic.IOUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferDemo {
    public static final Logger logger = LoggerFactory.getLogger(MappedByteBufferDemo.class);

    public static void main(String[] args) throws IOException {
        String path = "/samples/nio/poem.txt";
        String filePath = IOUtility.getResourcePath(path);
        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
        FileChannel fc = raf.getChannel();
        long size = fc.size();
        logger.info("File Channel Size: " + size);
        logger.info("File Channel Position: " + fc.position());
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, size);
        logger.info("MappedByteBuffer's info" + mbb);
        String fileContent = "";
        while(mbb.hasRemaining()) {
            fileContent += (char)mbb.get();
        }
        logger.info("File Content: \n" + fileContent);
        logger.info("-------------------");
        for (int i = 0; i < mbb.limit()/2; i++) {

        }
        fc.close();
    }
}
