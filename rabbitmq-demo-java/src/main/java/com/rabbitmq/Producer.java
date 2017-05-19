package com.rabbitmq;


import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.lang.SerializationUtils;

/**
 * Created by honestAnt on 2017/5/17.
 */
public class Producer extends  EndPoint {
    public Producer(String endPointName) throws IOException{
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}
