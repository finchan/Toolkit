package com.xavier.pandora.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class IOUtility {
    public static final Logger log = LoggerFactory.getLogger(IOUtility.class);
    public static String getResourcePath(String resourcePath) {
        String path = ClassPath.class.getResource(resourcePath).getPath();
        log.info(path);
        return path;
    }

    public static String createResourcePath(String resourcePath) throws IOException {
        String path = ClassPath.class.getResource("/").getPath()+resourcePath.substring(1);
        String directory = path.substring(0, path.lastIndexOf("/"));
        File file = new File(directory);
        file.mkdirs();
        log.info(path);
        return path;
    }
}
