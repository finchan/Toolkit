package com.xavier.pandora.activemq.amq03nioconnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.text.DecimalFormat;

public class Listener implements MessageListener {
    private static Logger log = LoggerFactory.getLogger(MessageListener.class);
    public void onMessage(Message message) {
        try {
            MapMessage map = (MapMessage)message;
            String stock = map.getString("stock");
            double price = map.getDouble("price");
            double offer = map.getDouble("offer");
            boolean up = map.getBoolean("up");
            DecimalFormat df = new DecimalFormat( "#,###,###,##0.00" );
            log.info(stock + "\t" + df.format(price) + "\t" + df.format(offer) + "\t" + (up?"up":"down"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
