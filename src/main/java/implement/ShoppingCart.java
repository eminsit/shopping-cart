package implement;

import enums.DiscountType;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Cart{
    private List<Item> items = new ArrayList<Item>();
    private List<Campaign> campaigns = new ArrayList<Campaign>();
    private Coupon coupon;
    private Double totalCost;
    private Double campaignDiscount = 0.0;
    private Double couponDiscount = 0.0;

    private Map<String, List<Item>> categories = new HashMap<String, List<Item>>();

    @Override
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

    @Override
    public void applyDiscount(Campaign campaign1) {
        campaigns.add(campaign1);

        calculateTotalCost();
        calculateCampaignDiscount();
    }

    @Override
    public void applyCoupon(Coupon coupon) {
        this.coupon = coupon;

        calculateTotalCost();
        calculateCouponDiscount();
    }

    @Override
    public void calculateTotalCost() {
        Double total = 0.0;
        for (Item item:items) {
            total += item.getItemCost();
        }

        totalCost = total;
    }

    @Override
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

    @Override
    public void calculateCouponDiscount() {
        calculateTotalCost();
        calculateCampaignDiscount();
        couponDiscount = coupon.getDiscountValue(totalCost - campaignDiscount);
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

    @Override
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
        products += "Total Cost: " + totalCost + "\n" +
                    "Campaign Discount: " + campaignDiscount + "\n" +
                    "Campaign Discount: " + couponDiscount + "\n" +
                    "Total Amount: " + (totalCost - couponDiscount - campaignDiscount) + "\n";
        System.out.println(products);
    }

    public List<Item> getItems() {
        return items;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Double getCampaignDiscount() {
        return campaignDiscount;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public Map<String, List<Item>> getCategories() {
        return categories;
    }
}
