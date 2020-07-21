package com.xavier.pandora.activemq.amq04updtcpconnector;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * Default - <transportConnector name="openwire" uri="tcp://0.0.0.0:61616?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
 * You can change connector name - <transportConnector  name="tcp" uri="tcp://0.0.0.0:61616?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
 * tcp://localhost:61616
 */
public class Consumer {
    private static Logger log = LoggerFactory.getLogger(Consumer.class);
    private static String brokerURL = "tcp://localhost:61616";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient Session session;
    private String jobs[] = new String[] {"suspend", "delete"};

    public Consumer() throws JMSException {
        //For ObjectMessage Serializable
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
        factory = new ActiveMQConnectionFactory(brokerURL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public Session getSession() {
        return session;
    }

    public static void main(String[] args) throws JMSException {
        Consumer consumer = new Consumer();
        for(String job : consumer.jobs){
            String queueName = "JOBS." +job;
            log.info(queueName);
            Destination destination = consumer.session.createQueue(queueName);
            MessageConsumer messageConsumer = consumer.session.createConsumer(destination);
            messageConsumer.setMessageListener(new Listener(job));
        }
    }
}
