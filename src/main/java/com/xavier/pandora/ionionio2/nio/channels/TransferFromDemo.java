package com.xavier.pandora.ionionio2.nio.channels;

import com.xavier.pandora.basic.IOUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferFromDemo {
    public static final Logger logger = LoggerFactory.getLogger(TransferFromDemo.class);

    public static void main(String[] args) throws IOException {
        try (FileInputStream fis =
                     new FileInputStream(IOUtility.getResourcePath("/samples/nio/poem.txt"));
             FileOutputStream fos =
                     new FileOutputStream(IOUtility.createResourcePath("/samples/nio/transfer.dat"));) {
            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel();
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        }
    }
}
