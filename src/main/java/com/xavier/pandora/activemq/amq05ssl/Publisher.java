package com.xavier.pandora.activemq.amq05ssl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Hashtable;
import java.util.Map;

/**
 * <transportConnector name="ssl" uri="ssl://0.0.0.0:61619?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
 * It doesn't work!!!!! Why????
 */
public class Publisher {
    private static Logger log = LoggerFactory.getLogger(Publisher.class);

    protected int MAX_DELTA_PERCENT = 1;
    protected Map<String, Double> LAST_PRICES = new Hashtable<String, Double>();
    protected static int count = 10;
    protected static int total;

    protected static String brokerURL = "ssl://127.0.0.1:61619";
    protected static transient ConnectionFactory factory;
    protected transient Connection connection;
    protected transient Session session;
    protected transient MessageProducer producer;

    public Publisher() throws JMSException {
        factory = new ActiveMQConnectionFactory(brokerURL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(null);
    }

    public static void main(String[] args) throws JMSException {
        //JSSE with ActiveMQ default keyStore and trustStore - ${ACTIVEMQ_HOME}/conf/
//        System.setProperty("javax.net.ssl.keyStore", "F:\\Practice\\Toolkit\\src\\main\\java\\com\\xavier\\pandora\\activemq\\amq05ssl\\client.ks");
//        System.setProperty("javax.net.ssl.keyStorePassword", "password");
//        System.setProperty("javax.net.ssl.trustStore", "F:\\Practice\\Toolkit\\src\\main\\java\\com\\xavier\\pandora\\activemq\\amq05ssl\\client.ts");
        System.setProperty("javax.net.ssl.keyStore", "D:\\Development\\apache-activemq-5.15.13\\conf\\myclient.ks");
        System.setProperty("javax.net.ssl.keyStorePassword", "pass1234");
        System.setProperty("javax.net.ssl.trustStore", "D:\\Development\\apache-activemq-5.15.13\\conf\\myclient.ts");

        String arguments[] = new String[]{"CSCO", "ORCL"};
        Publisher publisher = new Publisher();
        while (total < 1000) {
            for (int i = 0; i < count; i++) {
                publisher.sendMessage(arguments);
            }
            total += count;
            log.info("Published '" + count + "' of '" + total + "' price messages");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException x) {
            }
        }
        publisher.close();
    }

    public void close() throws JMSException {
        if(connection != null) {
            connection.close();
        }
    }

    protected void sendMessage(String[] stocks) throws JMSException {
        int idx = 0;
        while(true) {
            idx = (int) Math.round(stocks.length*Math.random());
            if(idx < stocks.length) {
                break;
            }
        }
        String stock = stocks[idx];
        String topicName = "STOCK." + stock;
        log.info("Topic - " + topicName);
        Destination destination = session.createTopic(topicName);
        Message message = createStockMessage(stock, session);
        log.info("Sending: " + ((ActiveMQMapMessage)message).getContentMap() + " on destination: " + destination);
        producer.send(destination, message);
    }

    protected Message createStockMessage(String stock, Session session) throws JMSException {
        Double value = LAST_PRICES.get(stock);
        if (value == null) {
            value = new Double(Math.random() * 100);
        }
        double oldPrice = value.doubleValue();
        value = new Double(mutatePrice(oldPrice));
        LAST_PRICES.put(stock, value);
        double price = value.doubleValue();
        double offer = price * 1.001;
        boolean up = (price > oldPrice);
        MapMessage message = session.createMapMessage();
        message.setString("stock", stock);
        message.setDouble("price", price);
        message.setDouble("offer", offer);
        message.setBoolean("up", up);
        return message;
    }

    protected double mutatePrice(double price) {
        double percentChange = (2 * Math.random() * MAX_DELTA_PERCENT) - MAX_DELTA_PERCENT;
        return price * (100 + percentChange) / 100;
    }
}
