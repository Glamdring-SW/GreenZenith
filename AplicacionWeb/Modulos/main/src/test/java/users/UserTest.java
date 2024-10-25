package users;

import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.userInteractions.users.User;

public class UserTest {

    public static void main(String[] args) {
        String username = "Exz";
        String email = "esotilin@gmail.com";
        String password = "1234";
        int age = 16;

        try {
            User user = new User(username, email, password, age);
        } catch (InvalidUserException | GZDBResultException e) {
            e.printStackTrace();
        }
    }
}
