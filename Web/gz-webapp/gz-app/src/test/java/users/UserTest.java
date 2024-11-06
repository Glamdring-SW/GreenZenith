package users;

import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.userInteractions.users.User;

public class UserTest {

    public static void main(String[] args) {
        try {
            User user = new User("joshupaultellesmjtpm@gmail.com", "StarcolvboderS#$123");
            System.out.println("\n" + user.getEmail());
            user.setName("sexo tilin");
            System.out.println("\n" + user.getName());
        } catch (InvalidUserException e) {
            e.printStackTrace();
        } catch (GZDBResultException e) {
            e.printStackTrace();
        }
    }
}
