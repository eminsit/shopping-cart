import enums.DiscountType;
import implement.Discount;
import implement.DiscountFactory;
import implement.ShoppingCart;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import utility.DeliveryCostCalculator;

import static org.junit.Assert.assertEquals;

public class DevliveryCalculatorTest {
    private DeliveryCostCalculator deliveryCostCalculator;

    @InjectMocks
    private ShoppingCart cart;

    @Mock
    private Coupon coupon;

    private Item item;

    private Category foodCategory;
    private Category deviceCategory;
    private Product product;
    private Product product2;
    private Product product3;
    private Campaign deviceCampaign;
    private Campaign foodCampaign;
    private Coupon rateCoupon;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
        foodCategory = new Category("food");
        deviceCategory = new Category("device");
        deviceCampaign = new Campaign(deviceCategory, 100.0, 2, DiscountType.Amount);
        foodCampaign = new Campaign(foodCategory, 10.0, 5, DiscountType.Rate);

        product = new Product("Apple", 10.0, foodCategory);
        product2 = new Product("Iphone", 1000.0, deviceCategory);
        product3 = new Product("Mi 9T", 100.0, deviceCategory);

        cart.addItem(product, 2);
        cart.addItem(product2, 1);
        cart.addItem(product3, 3);

        deliveryCostCalculator = new DeliveryCostCalculator(12.0, 10.0, 100.0);
    }

    @Test
    public void shouldCalculateDeliveryCost() {
        Double deliveryCost = deliveryCostCalculator.calculate(cart);
        assertEquals((Double) 154.0, deliveryCost);
    }
}