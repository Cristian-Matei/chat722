package chat.repository;

import chat.model.User;

public interface UserRepository extends ICrudRepository<String, User> {

    // add more methods specific to user Repo
    User findByUsernameAndPassword(String username, String password);
}
