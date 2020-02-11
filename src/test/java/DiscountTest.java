import enums.DiscountType;
import implement.Discount;
import implement.DiscountFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiscountTest {
    private Discount amountDiscount;
    private Discount rateDiscount;
    private DiscountFactory factory;
    private Double discountRate;
    private Double totalCost;

    @Before
    public void setUp() {
        factory = new DiscountFactory();
        rateDiscount = factory.getDiscount(DiscountType.Rate);
        amountDiscount = factory.getDiscount(DiscountType.Amount);
        totalCost = 100.0;
        discountRate = 20.0;
    }

    @Test
    public void amountDiscountShoulReturnSame() {
        Double result = amountDiscount.calculate(discountRate, totalCost);
        assertEquals(discountRate, result);
    }

    @Test
    public void rateDiscountShoulReturnSame() {
        Double result = amountDiscount.calculate(discountRate, totalCost);
        Double expected = discountRate * totalCost / 100;
        assertEquals(expected, result);
    }
}