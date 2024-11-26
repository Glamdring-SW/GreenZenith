package com.glamdring.greenZenith.userInteractions.users.messages;

import com.glamdring.greenZenith.exceptions.application.user.constants.MessageExceptions;
import com.glamdring.greenZenith.exceptions.application.user.messages.InvalidMessageException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;
import com.glamdring.greenZenith.userInteractions.users.User;
import com.google.protobuf.Timestamp;

/**
 * Manages the delivery and receival of messages.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.2
 */
public class Message {

    private int id;
    private User sender;
    private User reciever;
    private String content;
    private Timestamp sent;

    public Message(int id, GZDBConnector gzdbc) {

    }

    public Message(User sender, User reciever, String content, Timestamp sent) throws InvalidMessageException {
        try {
            sender.resetMaps();
            sender.insertMap.put("Content", content);
            sender.insertMap.put("Sent", sent);
            sender.insertMap.put("SenderID", sender.getId());
            sender.insertMap.put("RecieverID", reciever.getId());
            sender.gzdbc.insert(GZDBTables.CART, sender.insertMap);
            sender.resultList = sender.gzdbc.select(GZDBTables.CART, sender.insertMap);
            this.id = (int) sender.resultList.get(0).get("ID");
            this.content = content;
            this.sent = sent;
            this.sender = sender;
            this.reciever = reciever;
        } catch (GZDBResultException e) {
            throw new InvalidMessageException(MessageExceptions.LENGTH, e);
        }
    }

    public int getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReciever() {
        return reciever;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getSent() {
        return sent;
    }

}
