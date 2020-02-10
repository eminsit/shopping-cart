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
    private Double totalAmount = 0.0;

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
        totalAmount = totalCost - campaignDiscount;
    }

    public void calculateCouponDiscount() {
        calculateTotalCost();
        calculateCampaignDiscount();
        couponDiscount = coupon.getDiscountValue(totalAmount);
        totalAmount = totalAmount - campaignDiscount;
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

    public void print() {
        String products = "";
        for (Map.Entry<String, List<Item>> entry : categories.entrySet()) {
            String categoryName = entry.getKey();
            List<Item> itemList = entry.getValue();
            for (Item item: itemList) {
                products += "Category: " + categoryName +
                    ", Product Name: " + item.getProduct().getTitle() +
                    ", Product Count: " + item.getCount() +
                    ", Totatl Cost: " + item.getItemCost() + "\n";
            }
        }
        products += "Total Cost: " + totalCost + "\n";
        products += "Total Amount: " + totalAmount + "\n";
        System.out.println(products);
    }

    public List<Item> getItems() {
        return items;
    }

    public Map<String, List<Item>> getCategories() {
        return categories;
    }
}
