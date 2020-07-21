package com.xavier.pandora.activemq.amq03nioconnector;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Consumer {
    private static Logger log = LoggerFactory.getLogger(Consumer.class);
    private static String brokerURL = "tcp://127.0.0.1:61616";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient Session session;

    public Consumer() throws JMSException {
        factory = new ActiveMQConnectionFactory(brokerURL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws JMSException {
        String[] arguments = new String[]{"CSCO", "ORCL"};
        Consumer consumer = new Consumer();
        for (String stock : arguments) {
            String topicName = "STOCK." + stock;
            log.info(topicName);
            Destination destination = consumer.getSession().createTopic(topicName);
            MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);
            messageConsumer.setMessageListener(new Listener());
        }
    }

    public Session getSession() {
        return session;
    }
}
