import enums.DiscountType;
import model.Campaign;
import model.Category;
import model.Item;
import model.Product;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class CampaignTest {

    private Item item;

    private Campaign rateCampaign;
    private Campaign amountCampaign;
    Category foodCategory;
    Category deviceCategory;
    Product product;

    @Before
    public void setUp() {
        foodCategory = new Category("food");
        deviceCategory = new Category("device");
        product = new Product("Apple", 100.0, foodCategory);
        item = new Item(product, 10);
    }

    @Test
    public void rateTypeDiscountAndCampaignCategoryIsNotSameAsProductShouldReturnZero() {
        rateCampaign = new Campaign(deviceCategory, 20.0, 3, DiscountType.Rate);
        Double discount = rateCampaign.getDiscountValue(item);
        Double expected = 0.0;
        assertEquals(expected, discount);
    }

    @Test
    public void rateTypeDiscountAndCampaignCategoryIsSameAsProductShouldReturn() {
        rateCampaign = new Campaign(foodCategory, 20.0, 3, DiscountType.Rate);
        Double discount = rateCampaign.getDiscountValue(item);
        Double expected = 200.0;
        assertEquals(expected, discount);
    }

    @Test
    public void amountTypeDiscountAndCampaignCategoryIsSameAsProductShouldReturn() {
        amountCampaign = new Campaign(foodCategory, 20.0, 3, DiscountType.Amount);
        Double discount = amountCampaign.getDiscountValue(item);
        Double expected = 20.0;
        assertEquals(expected, discount);
    }
}