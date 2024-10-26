package users;

import com.glamdring.greenZenith.controllers.UserController;


public class UserTest {

    public static void main(String[] args) {
        String username = "user1";
        String email = "user1@gmail.com";
        String password = "1234";
        int age = 17;

        UserController controller = new UserController();

        boolean created = controller.createUser(username, email, password, age);
        if (created==true){
            System.out.println("user created");
        } else {
            System.out.println("invalid data, try again");
        }

    }
}
