package com.xavier.pandora.activemq.amq04updtcpconnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class Listener implements MessageListener {
    private static Logger log = LoggerFactory.getLogger(Listener.class);
    private String job;

    public Listener(String job) {
        this.job = job;
    }

    @Override
    public void onMessage(Message message) {
        try {
            log.info(job + " id: " + ((ObjectMessage) message).getObject());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
