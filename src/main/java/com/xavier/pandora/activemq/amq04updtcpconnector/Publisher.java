package com.xavier.pandora.activemq.amq04updtcpconnector;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * <transportConnector  name="udp" uri="udp://0.0.0.0:61617?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
 *  Exception: org.apache.activemq.transport.InactivityIOException: Channel was inactive (no connection attempt made) for too (>30000) long: /127.0.0.1:52734
 *  Cannot be resolved!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class Publisher {
    private static Logger log = LoggerFactory.getLogger(Publisher.class);
    private static String brokerURL = "udp://localhost:61617?wireFormat.maxInactivityDuration=0";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient Session session;
    private transient MessageProducer producer;

    private static int count = 10;
    private static int total;
    private static int id = 1000000;

    private String jobs[] = new String[]{"suspend", "delete"};

    public Publisher() throws JMSException {
        factory = new ActiveMQConnectionFactory(brokerURL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(null);
    }

    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    public void sendMessage() throws JMSException {
        int idx = 0;
        while(true) {
            idx = (int) Math.round(jobs.length*Math.random());
            if(idx < jobs.length)
                break;
        }
        String job = jobs[idx];
        String queueName = "JOBS." +job;
        Destination destination = session.createQueue(queueName);
        Message message = session.createObjectMessage(id++);
        log.info("Sending: id: " + ((ObjectMessage)message).getObject() + " on Queue: " + destination);
        producer.send(destination, message);
    }

    public static void main(String[] args) throws JMSException {
        Publisher publisher = new Publisher();
        while(total < 1000) {
            for(int i=0; i< count; i++){
                publisher.sendMessage();
            }
            total += count;
            log.info("Published " + count + " of " + total + " job messages");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        publisher.close();
    }
}
