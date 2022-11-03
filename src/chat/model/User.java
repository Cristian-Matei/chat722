package chat.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private boolean status; //True -> logged in, False -> otherwise

    private List<User> friends;
    private List<Message> received;
    private List<Message> pending;

    private Server server;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = false;
        this.friends = new ArrayList<>();
        this.received = new ArrayList<>();
        this.pending = new ArrayList<>();
    }

    public void sendMessage(String text, String username){
        // first check if the users are friends
        boolean found = false;
        User friend = null;
        for (User u: this.getFriends()) {
            if (u.username.equals(username)) {
                found = true;
                friend = u;
                break;
            }
        }
        if(found){
            Message message = new Message(this, friend, text);
            server.sendMessage(message);

        }
        else{
            System.out.println("YOU ARE NOT FRIENDS!");
        }
        //Message message = new Message()
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isOnline() { //returns true if user is online
        return status;
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<Message> getReceived() {
        return received;
    }

    public List<Message> getPending() {
        return pending;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
