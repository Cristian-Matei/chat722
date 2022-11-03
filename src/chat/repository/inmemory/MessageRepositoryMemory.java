package chat.repository.inmemory;

import chat.model.Message;
import chat.model.MessageStatus;
import chat.model.User;
import chat.repository.MessageRepository;
import chat.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryMemory implements MessageRepository {

    private List<Message> allMessages;
    private UserRepository userRepository;

    public MessageRepositoryMemory() {
        this.allMessages = new ArrayList<>();
        populateMessages();
    }

    private void populateMessages(){
        User maria = userRepository.findById("maria");
        User ion = userRepository.findById("ion");
        Message message1 = new Message(ion, maria, "Sal. Cf?", this.allMessages.size());
        message1.setStatus(MessageStatus.SENT);
        allMessages.add(message1);
        Message message2 = new Message(maria, ion, "Bn. Tu?", allMessages.size());
        message2.setStatus(MessageStatus.SENT);
        allMessages.add(message2);
    }

    @Override
    public void add(Message message) {
        for(Message m: allMessages){
            if(m.getId() == message.getId()){
                return;
            }
        }
        allMessages.add(message);
    }

    @Override
    public void remove(Integer integer) {

    }

    @Override
    public void update(Integer id, Message newMessage) {
        Message message = findById(id);
        if(message != null){
            int position = allMessages.indexOf(message);
            allMessages.set(position, newMessage); // updates the element in position "position"
        }
    }

    @Override
    public Message findById(Integer id) {
        for(Message m: allMessages){
            if(m.getId() == id){
                return m;
            }
        }
        return null;
    }
}
