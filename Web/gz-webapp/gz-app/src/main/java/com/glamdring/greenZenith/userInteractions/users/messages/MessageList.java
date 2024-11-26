package com.glamdring.greenZenith.userInteractions.users.messages;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.glamdring.greenZenith.exceptions.application.user.constants.MessageExceptions;
import com.glamdring.greenZenith.exceptions.application.user.messages.InvalidMessageException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.externals.database.GZDBConnector;
import com.glamdring.greenZenith.externals.database.constants.GZDBTables;

/**
 * Manages the delivery and receival of messages in a bunch.
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.3
 */
public class MessageList {

    /**
     * A list containing all the specified plants.
     */
    ArrayList<Message> messageList = new ArrayList<>();
    /**
     * A list containing all the specified plants by using their IDs as keys.
     */
    LinkedHashMap<Integer, Message> messageMap = new LinkedHashMap<>();

     public MessageList(GZDBConnector gzdbc) throws InvalidMessageException {
        try {
            ArrayList<LinkedHashMap<String, Object>> messageListDB = gzdbc.selectAll(GZDBTables.MESSAGE);
            if (!messageListDB.isEmpty()) {
                for (LinkedHashMap<String, Object> message : messageListDB) {
                    int id = (int) message.get("ID");
                    messageList.add(new Message(id, gzdbc));
                    messageMap.put(id, new Message(id, gzdbc));
                }
            }
        } catch (GZDBResultException e) {
            throw new InvalidMessageException(MessageExceptions.CONNECTION, e);
        }
    }
}
