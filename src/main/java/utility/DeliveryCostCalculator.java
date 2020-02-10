package utility;

import model.ShoppingCart;

public class DeliveryCostCalculator {
    private Double costPerDelivery;
    private Double costPerProduct;
    private Double fixCost;

    public DeliveryCostCalculator(Double costPerDelivery, Double costPerProduct, Double fixCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixCost = fixCost;
    }

    public Double calculate(ShoppingCart cart) {
        return (costPerDelivery * cart.getCategories().size()) + (costPerProduct * cart.getItems().size()) + fixCost;
    }
}
