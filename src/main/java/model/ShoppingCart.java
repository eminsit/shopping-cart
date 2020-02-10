package model;

import enums.DiscountType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private List<Item> items = new ArrayList<Item>();
    private List<Campaign> campaigns = new ArrayList<Campaign>();
    private Coupon coupon;
    private Double totalCost;
    private Double campaignDiscount = 0.0;
    private Double couponDiscount = 0.0;

    private Map<String, List<Item>> categories = new HashMap<String, List<Item>>();

    public void addItem(Product product, Integer productCount) {
        Item item = new Item(product, productCount);
        Category category = product.getCategory();
        for (Integer index = 0; index < items.size(); index++) {
            if (items.get(index).getProduct() == product) {
                item = items.get(index);
                items.remove(item);
                item.setCount(item.getCount() + productCount);
            }
        }

        List<Item> categoryItems = new ArrayList<Item>();
        categoryItems.add(item);
        if (categories.containsKey(category.getTitle())) {
            categoryItems.addAll(categories.get(category.getTitle()));
        }
        categories.put(category.getTitle(), categoryItems);

        items.add(item);
    }

    public void applyDiscounts(Campaign campaign1, Campaign campaign2, Campaign campaign3) {
        campaigns.add(campaign1);
        campaigns.add(campaign2);
        campaigns.add(campaign3);

        calculateTotalCost();
        calculateCampaignDiscount();
    }

    public void applyDiscounts(List<Campaign> campaigns) {
        this.campaigns.addAll(campaigns);

        calculateTotalCost();
        calculateCampaignDiscount();
    }

    public void applyCoupon(Coupon coupon) {
        this.coupon = coupon;

        calculateTotalCost();
        calculateCouponDiscount();
    }

    private void calculateTotalCost() {
        Double total = 0.0;
        for (Item item:items) {
            total += item.getItemCost();
        }

        totalCost = total;
    }

    public void calculateCampaignDiscount() {
        for (Campaign campaign:campaigns ) {
            Double discount = 0.0;
            Integer itemCount = 0;
            for (Item item: items) {
                if (item.getProduct().getCategory() == campaign.getCategory()) {
                    discount += campaign.getDiscountValue(item);
                    itemCount += item.getCount();
                }
            }
            if (discount > campaignDiscount && itemCount >= campaign.getItemCount()) {
                campaignDiscount = discount;
            }
        }
    }

    public void calculateCouponDiscount() {
        calculateTotalCost();
        calculateCampaignDiscount();
        Double priceAfterCampaignDiscount = totalCost - campaignDiscount;
        couponDiscount = coupon.getDiscountValue(priceAfterCampaignDiscount);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                ", campaigns=" + campaigns +
                ", coupon=" + coupon +
                ", totalCost=" + totalCost +
                ", campaignDiscount=" + campaignDiscount +
                ", couponDiscount=" + couponDiscount +
                '}';
    }

    public List<Item> getItems() {
        return items;
    }

    public Map<String, List<Item>> getCategories() {
        return categories;
    }
}
