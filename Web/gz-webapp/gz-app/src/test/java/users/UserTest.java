package users;

import com.glamdring.greenZenith.controllers.UserController;


public class UserTest {

    public static void main(String[] args) {
        String username = "TEST1";
        String email = "TESTEMAIL1";
        String password = "TESTPASSWORD";
        int age = 0;

        UserController controller = new UserController();

        boolean created = controller.summonUser(email, password);
        if (created==true){
            System.out.println("user created");
        } else {
            System.out.println("invalid data, try again");
        }

        System.out.println(controller.getUserName());
        System.out.println(controller.getUserAge());
        System.out.println(controller.getUserEmail());

    }
}
