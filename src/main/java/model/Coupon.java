package model;

import enums.DiscountType;

public class Coupon {
    private Integer minAmount;
    private Double discount;
    private DiscountType discountType;

    public Coupon(Integer minAmount, Double discount, DiscountType discountType) {
        this.minAmount = minAmount;
        this.discount = discount;
        this.discountType = discountType;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountValue(Double amount) {
        Double discount = 0.0;

        if (discountType == DiscountType.Rate) {
            discount = (this.discount * amount ) / 100;
        } else if (discountType == DiscountType.Rate) {
            discount = amount-this.discount;
        }

        return discount;
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
