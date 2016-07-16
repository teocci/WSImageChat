package com.teocci.websocket;

import com.teocci.data.MessageData;
import com.teocci.data.MessageDecoder;
import com.teocci.data.MessageEncoder;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by teocci on 7/16/16.
 *
 * @author Jorge Frisancho
 * WSChatServer.java
 * Endpoint for the chat server using Websockets as the means of communication.
 * Messages are passed as Json strings using the MessageData class and the associated
 * encoder and decoder classes.
 */

@ServerEndpoint(value="/wschat", encoders={MessageEncoder.class}, decoders={MessageDecoder.class})
public class WSChatServer {
    // Set of all the connected sessions
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
        sessions.add(session);

        MessageData messageData = new MessageData(Json.createObjectBuilder()
                .add("type", "text")
                .add("data", "User has connected")
                .build());

        for(Session s : sessions){
            try {
                s.getBasicRemote().sendObject(messageData);
            } catch (IOException | EncodeException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(session.getId() + " has connected");
    }

    /**
     * When a user sends a messageData to the server, this method will intercept the messageData
     * and allow us to react to it. For now the messageData is read as a String.
     */
    @OnMessage
    public void onMessage(MessageData messageData, Session session){
        System.out.println(messageData);
        for(Session s : sessions){
            try {
                s.getBasicRemote().sendObject(messageData);
            } catch (IOException | EncodeException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(messageData);
    }

    /**
     * The user closes the connection.
     *
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        sessions.remove(session);

        MessageData messageData = new MessageData(Json.createObjectBuilder()
                .add("type", "text")
                .add("data", "User has disconnected")
                .build());

        for(Session s : sessions){
            try {
                s.getBasicRemote().sendObject(messageData);
            } catch (IOException | EncodeException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("User disconnected");
    }
}