package users;

import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.userInteractions.plants.Plant;
import com.glamdring.greenZenith.userInteractions.users.User;

public class UserTest {

    public static void main(String[] args) {
        try {

            User user = new User(1);

            user.updatePlantBatch(6, "Vcvzxc", null, null, 0, null, null, user.getPictureHandler().getDEFAULT_TRUE());
            System.out.println(user.getPictureHandler().getDEFAULT_TRUE().equals(user.getPlant(6, "Vcvzxc").getPicture()));
            System.out.println(user.getPictureHandler().getDEFAULT_PLANT().equals(user.getPlant(6, "Vcvzxc").getPicture()));
            System.out.println(user.getPictureHandler().getDEFAULT_TRUE());
            System.out.println(user.getPictureHandler().getDEFAULT_PLANT());
            System.out.println(user.getPlant(6, "Vcvzxc").getPicture());

        } catch (InvalidUserException | GZDBResultException ex) {
            ex.printStackTrace();
        }
    }

    public static void getMessages(User user1, User user2, User user4, Plant plant1, Plant plant4) {
        System.out.println(user1.getName());
        System.out.println(user1.getEmail());
        System.out.println(user1.getAge());

        System.out.println(user2.getName());
        System.out.println(user2.getEmail());
        System.out.println(user2.getAge());

        System.out.println(user4.getName());
        System.out.println(user4.getEmail());
        System.out.println(user4.getAge());

        System.out.println(plant1.getName());
        System.out.println(plant1.getDescription());
        System.out.println(plant1.getPlantingDate());
        System.out.println(plant1.getSchedule());

        System.out.println(plant4.getName());
        System.out.println(plant4.getDescription());
        System.out.println(plant4.getPlantingDate());
        System.out.println(plant4.getSchedule());
    }
}
