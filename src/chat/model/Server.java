package chat.model;

import chat.repository.UserRepository;

public class Server {

    private UserRepository userRepository;
    //private
    public void login(String username, String password){
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(!(user==null)){
            user.setStatus(true);
        }

    }

    public void sendMessage(Message message){
        User receiver = message.getReceiver();
        if(receiver.isOnline()){ // add this message to the received messages list
            message.setStatus(MessageStatus.SENT);

        }
    }
}
