package implement;

import model.Campaign;
import model.Coupon;
import model.Product;

import java.util.List;

public interface Cart {

    void addItem(Product product, Integer productCount);

    void applyDiscounts(Campaign campaign1, Campaign campaign2, Campaign campaign3);

    void applyDiscounts(List<Campaign> campaigns);

    void applyCoupon(Coupon coupon);

    void calculateTotalCost();

    void calculateCampaignDiscount();

    void calculateCouponDiscount();

    void print();

}
