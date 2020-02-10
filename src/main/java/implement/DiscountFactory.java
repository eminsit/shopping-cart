package implement;

import enums.DiscountType;

public class DiscountFactory {
    public Discount getDiscount(DiscountType discountType) {
        if(discountType == null){
            return null;
        }
        if(discountType == DiscountType.Rate) {
            return new RateDiscount();

        } else if(discountType == DiscountType.Amount) {
            return new AmountDiscount();
        }

        return null;
    }
}
