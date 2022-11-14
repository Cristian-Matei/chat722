package chat.model;

import java.util.Date;

public class Message {

    private int id;
    private User sender;
    private User receiver;
    private String text;
    private Date date;
    private MessageStatus status;

    public Message(User sender, User receiver, String text, int id) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.date = new Date(); //gibt den jetzigen Datum und Zeit zuruck
        this.status = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
