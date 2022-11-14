package test;

import chat.controller.Server;
import chat.model.Message;
import chat.model.User;
import chat.repository.MessageRepository;
import chat.repository.UserRepository;
import chat.repository.inmemory.MessageRepositoryMemory;
import chat.repository.inmemory.UserRepositoryMemory;
import chat.utils.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    private Server server;
    private UserRepository userRepository;
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp(){
        userRepository = new UserRepositoryMemory();
        messageRepository = new MessageRepositoryMemory(userRepository);
        server = new Server(userRepository, messageRepository);
    }

    @Test
    void testSuccessfulLogin() throws InvalidCredentialsException {
        assertTrue(server.login("ion", "1234"));
    }

    @Test
    void testUnsuccessfulLogin(){
        //assertFalse(server.login("ion", "12345"));
        assertThrows(InvalidCredentialsException.class, () -> server.login("ion", "12345"));
        Throwable exception = assertThrows(InvalidCredentialsException.class, () -> server.login("ion", "12345"));
        assertEquals(exception.getMessage(), "Credentials are not valid");

    }

    @Test
    void testTransferMessages() throws InvalidCredentialsException {
        server.login("ion", "1234");
        User sender = userRepository.findById("ion");
        User receiver = userRepository.findById("marie");
        server.sendMessage(new Message(sender, receiver, "Salut!!", 4));
        server.login("marie", "12345");
        assertEquals(receiver.getPending().size(), 0);
        assertTrue(receiver.getPending().isEmpty());
    }

    @Test
    void sendMessageToNonFriend() throws InvalidCredentialsException {
        server.login("ion", "1234");
        User sender = userRepository.findById("ion");
        User receiver = userRepository.findById("gheorghe");
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> server.sendMessage(new Message(sender, receiver, "Salut!!", 5) ));
        assertEquals(exception.getMessage(), "Sender is not valid as you are not friends");
        //server.login("marie", "12345");
    }


}