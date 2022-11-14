import chat.repository.UserRepository;
import chat.repository.file.UserJSONRepository;

public class Main {

    public static void main(String[] args) {
        UserJSONRepository repo = new UserJSONRepository("src/main/resources/users.json");
        System.out.println(repo.findById("ion"));
    }
}