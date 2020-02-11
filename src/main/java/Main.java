import enums.DiscountType;
import implement.ShoppingCart;
import model.*;

public class Main {
    public static void main(String[] args) {
        Category foodCategory = new Category("food");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almonds = new Product("Almonds", 150.0, foodCategory);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almonds, 1);

        Campaign campaign1 = new Campaign(foodCategory, 20.0, 3, DiscountType.Rate);
        Campaign campaign2 = new Campaign(foodCategory, 50.0, 5, DiscountType.Rate);
        Campaign campaign3 = new Campaign(foodCategory, 20.0, 3, DiscountType.Rate);

        cart.applyDiscount(campaign1);
        cart.applyDiscount(campaign2);
        cart.applyDiscount(campaign3);

        Coupon coupon = new Coupon(100, 10.0, DiscountType.Rate);
        cart.applyCoupon(coupon);

        cart.print();
    }
}
