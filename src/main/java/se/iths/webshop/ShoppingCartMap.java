package se.iths.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.iths.webshop.entity.Product;
import se.iths.webshop.service.ProductService;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShoppingCartMap {
    @Autowired
    private ProductService productService;

    private Map<Product, Integer> shoppingCart = new HashMap<>();

    public ShoppingCartMap() {
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void add(Product product, Integer quantity) {
        if (!shoppingCart.isEmpty()) {
            for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
                if (entry.getKey().getId() == product.getId()) {
                    int newQuantity = quantity + entry.getValue();
                    update(product, newQuantity);
                    break;
                } else {
                    shoppingCart.put(product, quantity);
                    break;
                }
            }
        } else {
            shoppingCart.put(product, quantity);
        }
    }

    public void update(Product product, Integer quantity) {
        Product productToUpdate = null;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            if (entry.getKey().getId() == product.getId()) {
                System.out.println("YAY!");
                productToUpdate = entry.getKey();
            }
            }
        shoppingCart.replace(productToUpdate, quantity);
        }

    public double calculatePrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            totalPrice += (entry.getKey().getPrice()) * entry.getValue();
        }
        return totalPrice;
    }

    public int getTotalItems() {
        int totalItems = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            totalItems += entry.getValue();
        }
        return totalItems;
    }
}
