package com.xavier.pandora.ionionio2.nio.channels;

import com.xavier.pandora.basic.IOUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class TransferToDemo {
    public static final Logger logger = LoggerFactory.getLogger(TransferToDemo.class);

    public static void main(String[] args) {
        try(FileInputStream fis = new FileInputStream(IOUtility.getResourcePath("/samples/nio/poem.txt"))){
            FileChannel inChannel = fis.getChannel();
            WritableByteChannel outChannel = Channels.newChannel(System.out);
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch(IOException e) {
            logger.info(e.getMessage());
        }
    }
}
