package implement;

public class RateDiscount implements Discount {
    @Override
    public Double calculate(Double discountValue, Double amount) {
        return (discountValue *  amount) / 100;
    }
}
