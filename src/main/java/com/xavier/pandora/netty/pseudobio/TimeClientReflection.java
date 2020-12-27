package com.xavier.pandora.netty.pseudobio;

import com.xavier.pandora.netty.bio.TimeClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class TimeClientReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TimeClient.class.getMethod("main", String[].class);
        method.invoke(null, (Object) new String[]{"HOURS"});
        String[] modes = {"TIME", "HOURS", "MINUTES", "OTHERS"};
        for (int i = 0; i < 1000; i++) {
            method.invoke(null, (Object) new String[]{modes[new Random().nextInt(modes.length)]});
        }
    }
}
