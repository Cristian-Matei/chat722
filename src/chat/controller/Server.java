package chat.controller;

import chat.model.Message;
import chat.model.MessageStatus;
import chat.model.User;
import chat.repository.MessageRepository;
import chat.repository.UserRepository;

public class Server {

    private UserRepository userRepository;
    private MessageRepository messageRepository;
    //private
    public void login(String username, String password){
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(!(user==null)){
            user.setStatus(true);
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
            if(receiver.isOnline()){
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
            System.out.println("YOU ARE NOT FRIENDS!");
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
