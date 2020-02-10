package model;

import implement.Discount;
import implement.DiscountFactory;
import enums.DiscountType;

public class Campaign {
    private Category category;
    private Double discountValue;
    private Integer itemCount;
    private DiscountType discountType;
    private Discount discount;

    public Campaign(Category category, Double discount, Integer itemCount, DiscountType discountType) {
        this.category = category;
        this.discountValue = discount;
        this.itemCount = itemCount;
        this.discountType = discountType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getDiscount() {
        return discountValue;
    }

    public void setDiscount(Double discount) {
        this.discountValue = discount;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountValue(Item item) {
        Double result = 0.0;
        if (item.getProduct().getCategory() != this.category) {
            return result;
        }

        DiscountFactory factory = new DiscountFactory();
        discount = factory.getDiscount(discountType);

        return discount.calculate(discountValue, item.getItemCost());
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "category=" + category +
                ", discount=" + discountValue +
                ", itemCount=" + itemCount +
                ", discountType=" + discountType +
                '}';
    }
}
