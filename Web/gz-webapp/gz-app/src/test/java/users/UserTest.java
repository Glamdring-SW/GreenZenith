package users;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.glamdring.greenZenith.exceptions.application.plant.InvalidPlantException;
import com.glamdring.greenZenith.exceptions.application.user.InvalidUserException;
import com.glamdring.greenZenith.exceptions.database.GZDBResultException;
import com.glamdring.greenZenith.userInteractions.plants.Plant;
import com.glamdring.greenZenith.userInteractions.users.User;

public class UserTest {

    public static void main(String[] args) {
        try {
            ArrayList<LocalTime> timeList1 = new ArrayList<>();
            timeList1.add(LocalTime.now());
            timeList1.add(LocalTime.MIDNIGHT);
            timeList1.add(LocalTime.NOON);

            ArrayList<LocalTime> timeList2 = new ArrayList<>();
            timeList1.add(LocalTime.now());
            timeList1.add(LocalTime.MAX);
            timeList1.add(LocalTime.MIN);

            User user1 = new User(1);

            Plant plant1 = new Plant(1, user1);

            String user1PlantUpdateMessage = user1.updatePlantBatch(plant1.getId(), plant1.getName(), "", LocalDate.EPOCH, "    ", 90, timeList2, null);

            System.out.println(user1PlantUpdateMessage);

        } catch (InvalidPlantException | InvalidUserException | GZDBResultException ex) {
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
