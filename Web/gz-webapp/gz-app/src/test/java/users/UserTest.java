package users;

import com.glamdring.greenZenith.userInteractions.products.CartProduct;
import com.glamdring.greenZenith.userInteractions.users.User;

public class UserTest {

    public static void main(String[] args) {
        try {
            User user = new User(1);
            for (CartProduct product : (user.getCart().getProductList())) {
                System.out.println(product.getProduct().getId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
