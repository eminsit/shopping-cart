import enums.DiscountType;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class CouponTest {

    private Item item;

    private Coupon rateCoupon;
    private Coupon amountCoupon;
    Category foodCategory;
    Category deviceCategory;
    Product product;

    @Before
    public void setUp() {
        foodCategory = new Category("food");
        deviceCategory = new Category("device");
        product = new Product("Apple", 100.0, foodCategory);
        item = new Item(product, 1);
    }

    @Test
    public void ifRateTypeCouponAndCouponMinItemCountIsGreaterShouldReturnZero() {
        rateCoupon = new Coupon(1000, 20.0, DiscountType.Rate);
        Double discount = rateCoupon.getDiscountValue(item.getItemCost());
        Double expected = 0.0;
        assertEquals(expected, discount);
    }

    @Test
    public void rateTypeDiscountAndCampaignCategoryIsSameAsProductShouldReturn() {
        rateCoupon = new Coupon(10, 20.0, DiscountType.Rate);
        Double discount = rateCoupon.getDiscountValue(item.getItemCost());
        Double expected = 20.0;
        assertEquals(expected, discount);
    }

    @Test
    public void amountTypeDiscountAndCampaignCategoryIsSameAsProductShouldReturn() {
        amountCoupon = new Coupon(10, 20.0, DiscountType.Amount);
        Double discount = amountCoupon.getDiscountValue(item.getItemCost());
        Double expected = 20.0;
        assertEquals(expected, discount);
    }
}