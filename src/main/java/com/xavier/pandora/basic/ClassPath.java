package com.xavier.pandora.basic;

import com.xavier.pandora.httpcomponents.samples.ClientCustomSSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassPath {
    private static Logger logger = LoggerFactory.getLogger(ClassPath.class);
    public static void main(String[] args) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        logger.info(rootPath);
        String rootPath2 = ClassPath.class.getResource("/").getPath();
        logger.info(rootPath2);
        String rootPath3 = ClassPath.class.getClassLoader().getResource("").getPath();
        logger.info(rootPath3);
        String rootPath4 = ClassLoader.getSystemResource("").getPath();
        logger.info(rootPath4);
        String currentPath = ClassPath.class.getResource("").getPath();
        logger.info(currentPath);
    }
}
