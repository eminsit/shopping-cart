package implement;

public class AmountDiscount implements Discount {
    @Override
    public Double calculate(Double discountValue, Double amount) {
        return discountValue;
    }
}
