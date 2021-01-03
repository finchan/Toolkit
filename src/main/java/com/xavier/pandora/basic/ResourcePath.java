package com.xavier.pandora.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourcePath {
    public static final Logger log = LoggerFactory.getLogger(ResourcePath.class);
    public static void main(String[] args) {
        String rootPath2 = ClassPath.class.getResource("/samples/nio/x.dat").getPath();
        log.info(rootPath2);
    }
}
