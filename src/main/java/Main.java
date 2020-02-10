import enums.DiscountType;
import model.*;

public class Main {
    public static void main(String[] args) {
        Category foodCategory = new Category("food");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almonds = new Product("Almonds", 150.0, foodCategory);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almonds, 1);
        //cart.addItem(almonds, 5);

        Campaign campaign1 = new Campaign(foodCategory, 20.0, 3, DiscountType.Rate);
        Campaign campaign2 = new Campaign(foodCategory, 50.0, 5, DiscountType.Rate);
        Campaign campaign3 = new Campaign(foodCategory, 20.0, 3, DiscountType.Rate);

        cart.applyDiscounts(campaign1, campaign2, campaign3);

        Coupon coupon = new Coupon(100, 10.0, DiscountType.Rate);
        cart.applyCoupon(coupon);

        System.out.println(cart);
    }
}
