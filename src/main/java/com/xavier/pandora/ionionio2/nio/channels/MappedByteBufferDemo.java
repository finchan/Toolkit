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
        logger.info("1. MappedByteBuffer's info" + mbb);
        String fileContent = "";
        while(mbb.hasRemaining()) {
            fileContent += (char)mbb.get();
        }
        logger.info("After get Data: " + mbb);
        logger.info("File Content: \n" + fileContent);
        logger.info("-------------------");
        for (int i = 0; i < mbb.limit()/2; i++) {
            byte b1 = mbb.get(i);
            logger.info("mbb get i - position: " + mbb.position());
            byte b2 = mbb.get(mbb.limit()-i-1);
            logger.info("mbb get limit()-i-1 - position: " + mbb.position());
            mbb.put(i, b2);
            logger.info("mbb put i - position: " + mbb.position());
            mbb.put(mbb.limit()-i-1, b1);
            logger.info("mbb put limit()-i-1 - position: " + mbb.position());
            logger.info("LOOPED - MappedByteBuffer's info" + mbb);
        }
        mbb.flip();
        logger.info("2. MappedByteBuffer's info" + mbb);
        String updateFileContent = "";
        while(mbb.remaining()>0) {
            updateFileContent += (char) mbb.get();
            logger.info("mbb get() - position: " + mbb.position());
            logger.info("mbb get() - limit: " + mbb.limit());
        }
        logger.info("\n"+updateFileContent);
        fc.close();
    }
}
