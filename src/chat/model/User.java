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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = false;
        this.friends = new ArrayList<>();
        this.received = new ArrayList<>();
        this.pending = new ArrayList<>();
    }

    public void addFriend(User user){ //adds a new friend for the current user
        this.friends.add(user);
        user.getFriends().add(this);
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
