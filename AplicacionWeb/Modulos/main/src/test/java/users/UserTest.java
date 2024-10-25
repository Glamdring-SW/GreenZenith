package users;

import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.userInteractions.users.User;

public class UserTest {

    public static void main(String[] args) {
        String username = "TEST";
        String email = "TESTEMAIL";
        String password = "TESTPASSWORD";
        int age = 17;

        try {
            User user = new User(username, email, password, age);
        } catch (InvalidUserException | GZDBResultException e) {
            e.printStackTrace();
        }
    }
}
