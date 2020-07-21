package com.xavier.pandora.activemq.amq05ssl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Consumer {
    private static Logger log = LoggerFactory.getLogger(Consumer.class);
    private static String brokerURL = "ssl://127.0.0.1:61619";
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

    //args - CSCO ORCL
    public static void main(String[] args) throws JMSException {
        //JSSE with ActiveMQ default keyStore and trustStore - ${ACTIVEMQ_HOME}/conf/
//        System.setProperty("javax.net.ssl.keyStore", "F:\\Practice\\Toolkit\\src\\main\\java\\com\\xavier\\pandora\\activemq\\amq05ssl\\client.ks");
//        System.setProperty("javax.net.ssl.keyStorePassword", "password");
//        System.setProperty("javax.net.ssl.trustStore", "F:\\Practice\\Toolkit\\src\\main\\java\\com\\xavier\\pandora\\activemq\\amq05ssl\\client.ts");
        System.setProperty("javax.net.ssl.keyStore", "D:\\Development\\apache-activemq-5.15.13\\conf\\myclient.ks");
        System.setProperty("javax.net.ssl.keyStorePassword", "pass1234");
        System.setProperty("javax.net.ssl.trustStore", "D:\\Development\\apache-activemq-5.15.13\\conf\\myclient.ts");

        String arguments[] = new String[]{"CSCO", "ORCL"};
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
