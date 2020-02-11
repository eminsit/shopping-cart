package model;

import implement.Discount;
import implement.DiscountFactory;
import enums.DiscountType;

public class Coupon {
    private Integer minAmount;
    private Double discountValue;
    private DiscountType discountType;
    private Discount discount;

    public Coupon(Integer minAmount, Double discountValue, DiscountType discountType) {
        this.minAmount = minAmount;
        this.discountValue = discountValue;
        this.discountType = discountType;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountValue(Double amount) {
        Double disc = 0.0;
        if (minAmount > amount) {
            return  disc;
        }

        DiscountFactory factory = new DiscountFactory();
        discount = factory.getDiscount(discountType);

        return discount.calculate(discountValue, amount);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "minAmount=" + minAmount +
                ", discount=" + discount +
                ", discountType=" + discountType +
                '}';
    }
}
