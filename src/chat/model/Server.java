package chat.model;

import chat.repository.UserRepository;

public class Server {

    private UserRepository userRepository;
    //private
    public void login(String username, String password){
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(!(user==null)){
            user.setStatus(true);
            if(!user.getPending().isEmpty()){
                // add all the messages from pending list to the received list
                user.getReceived().addAll(user.getPending()); // adds the whole pending list to the received one
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
                receiver.getReceived().add(message);
            }
            else{ //if receiver is not online, add this message to pending
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
