package implement;

import model.Campaign;
import model.Coupon;
import model.Product;

import java.util.List;

public interface Cart {

    void addItem(Product product, Integer productCount);

    void applyDiscount(Campaign campaign1);

    void applyCoupon(Coupon coupon);

    void calculateTotalCost();

    void calculateCampaignDiscount();

    void calculateCouponDiscount();

    void print();

}
