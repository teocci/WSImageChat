package com.teocci.data;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by teocci on 7/16/16.
 */
public class MessageEncoder implements Encoder.Text<MessageData> {

    @Override
    public String encode(MessageData messageData) throws EncodeException {
        return messageData.getJson().toString();
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("Init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}