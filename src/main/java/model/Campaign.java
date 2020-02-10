package model;

import enums.DiscountType;

public class Campaign {
    private Category category;
    private Double discount;
    private Integer itemCount;
    private DiscountType discountType;

    public Campaign(Category category, Double discount, Integer itemCount, DiscountType discountType) {
        this.category = category;
        this.discount = discount;
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
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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
        Double discount = 0.0;

        if (discountType == DiscountType.Rate) {
            discount = (this.discount * item.getItemCost() ) / 100;
        } else if (discountType == DiscountType.Rate) {
            discount = item.getItemCost() - this.discount;
        }

        return discount;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "category=" + category +
                ", discount=" + discount +
                ", itemCount=" + itemCount +
                ", discountType=" + discountType +
                '}';
    }
}
