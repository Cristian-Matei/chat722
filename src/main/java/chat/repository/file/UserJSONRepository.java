package chat.repository.file;

import chat.model.User;
import chat.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jdk.jshell.spi.ExecutionControlProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class UserJSONRepository implements UserRepository {

    private String filePath;

    public UserJSONRepository(String filePath) {
        this.filePath = filePath;
        this.populate();
    }

    public void populate() {

        try {    // create object mapper instance
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            User user1 = new User("ion", "1234");
            User user2 = new User("marie", "12345");
            User user3 = new User("gheorghe", "123456");
            List<User> users = Arrays.asList(user1, user2, user3);
            // / convert JSON string to Book object
            mapper.writeValue(Paths.get(filePath).toFile(),users);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void add(User entity) {

    }

    @Override
    public void remove(String s) {

    }

    @Override
    public void update(String s, User newEntity) {

    }

    @Override
    public User findById(String username) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //Variante 1
            User[] users = mapper.readValue(new File(filePath), User[].class);
            // Variante 2
            //List<User> usersList = Arrays.asList( mapper.readValue(new File(filePath), User[].class) );

            for (User u: users) {
                if(u.getUsername().equals(username)){
                    return u;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return null;
    }
}
