package com.teocci.data;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by teocci on 7/16/16.
 */
public class MessageDecoder implements Decoder.Text<MessageData>{

    @Override
    public MessageData decode(String string) throws DecodeException {
        JsonObject json = Json.createReader(new StringReader(string)).readObject();
        return new MessageData(json);
    }

    @Override
    public boolean willDecode(String string) {
        try{
            Json.createReader(new StringReader(string)).read();
            return true;
        }catch (JsonException ex){
            System.out.println(string);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}