import enums.DiscountType;
import implement.Discount;
import implement.DiscountFactory;
import implement.ShoppingCart;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;

public class ShoppingCartTest {
    @InjectMocks
    private ShoppingCart cart;

    private Item item;

    private Category foodCategory;
    private Category deviceCategory;
    private Product product;
    private Product product2;
    private Campaign deviceCampaign;
    private Campaign foodCampaign;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
        foodCategory = new Category("food");
        deviceCategory = new Category("device");
        deviceCampaign = new Campaign(deviceCategory, 100.0, 2, DiscountType.Amount);
        foodCampaign = new Campaign(foodCategory, 10.0, 5, DiscountType.Rate);

        product = new Product("Apple", 10.0, foodCategory);
        product2 = new Product("Iphone", 1000.0, deviceCategory);
        item = new Item(product, 1);
    }

    @Test
    public void cartItemListLengthShouldBeZeroAtBeginning() {
        assertEquals(0, cart.getItems().size());
    }

    @Test
    public void cartItemsShouldAddOne() {
        cart.addItem(product, 10);
        assertEquals(1, cart.getItems().size());
        assertEquals("Apple", cart.getItems().get(0).getProduct().getTitle());
        assertEquals((Integer)10, cart.getItems().get(0).getCount());
    }

    @Test
    public void cartItemsShouldAddMultiple() {
        cart.addItem(product, 10);
        cart.addItem(product2, 1);
        assertNotEquals(1, cart.getItems().size());
    }

    @Test
    public void cartShouldReturnCost() {
        cart.addItem(product, 10);
        cart.addItem(product2, 1);
        cart.calculateTotalCost();
        assertEquals((Double)1100.0, cart.getTotalCost());
    }

    @Test
    public void cartShouldNotApplyDiscountOnDifferentCategories() {
        cart.addItem(product2, 1);
        cart.applyDiscount(foodCampaign);
        assertEquals(cart.getItems().get(0).getItemCost(), product2.getPrice());
    }

    @Test
    public void cartShouldApplyDiscountOnSameCategories() {
        cart.addItem(product2, 1);
        cart.applyDiscount(deviceCampaign);
        assertEquals(, product2.getPrice());
    }
}