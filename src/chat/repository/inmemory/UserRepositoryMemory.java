package chat.repository.inmemory;

import chat.model.User;
import chat.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMemory implements UserRepository {


    private List<User> allUsers;

    public UserRepositoryMemory() {
        this.allUsers = new ArrayList<>();
        populateUsers();

    }
    public void populateUsers(){
        User user1 = new User("ion", "1234");
        User user2 = new User("marie", "12345");

        this.allUsers.add(user1);
        this.allUsers.add(user2);
    }

    @Override
    public void add(User entity) {
        for (User u: this.allUsers) {
            if (u.getUsername().equals(entity.getUsername())){
                return;
            }
        }
        this.allUsers.add(entity);
    }

    @Override
    public void remove(String s) {

    }

    @Override
    public void update(String s, User newEntity) {

    }

    @Override
    public User findById(String s) {
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return null;
    }
}
