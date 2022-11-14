package chat.controller;

import chat.model.Message;
import chat.model.MessageStatus;
import chat.model.User;
import chat.repository.MessageRepository;
import chat.repository.UserRepository;
import chat.utils.InvalidCredentialsException;

public class Server {

    private UserRepository userRepository;
    private MessageRepository messageRepository;

    public Server(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    //private
    public boolean login(String username, String password) throws InvalidCredentialsException {
        User user = userRepository.findByUsernameAndPassword(username, password);
        // throw an exception on invalid credentials
        if(user == null){
            throw new InvalidCredentialsException("Credentials are not valid");
        }

        user.setStatus(true);
        transferMessagesOnLogin(user);
        return true;
    }

    public void transferMessagesOnLogin(User user){
        if(!user.getPending().isEmpty()){
            // add all the messages from pending list to the received list
            user.getReceived().addAll(user.getPending()); // adds the whole pending list to the received one
            for (Message m: user.getReceived()) {
                m.setStatus(MessageStatus.SENT);
                //update this information also in repo
                messageRepository.update(m.getId(), m);

            }
            user.getPending().clear();
        }
    }

    public void sendMessage(Message message){

        boolean found = false;
        User sender = message.getSender();
        User receiver = message.getReceiver();

        // first check if the users are friends
        for (User u: sender.getFriends()) {
            if (u.getUsername().equals(receiver.getUsername())) {
                found = true;
                break;
            }
        }
        if(found){
            // check status of the receiver
            if(receiver.isStatus()){
                message.setStatus(MessageStatus.SENT);
                messageRepository.add(message);
                receiver.getReceived().add(message);
            }
            else{ //if receiver is not online, add this message to pending
                message.setStatus(MessageStatus.PENDING);
                messageRepository.add(message);
                receiver.getPending().add(message);
            }
        }
        else{
            throw new IllegalArgumentException("Sender is not valid as you are not friends");
        }
        //Message message = new Message()
    }

//    public void sendMessage(Message message){
//        User receiver = message.getReceiver();
//        if(receiver.isOnline()){ // add this message to the received messages list
//            message.setStatus(MessageStatus.SENT);
//
//        }
//    }
}
