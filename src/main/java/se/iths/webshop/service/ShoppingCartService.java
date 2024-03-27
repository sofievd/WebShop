package se.iths.webshop.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.iths.webshop.entity.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@SessionScope
public class ShoppingCartService {

    private HashMap<Product, Integer> shoppingCart;
    private Map<Product, List<Double>> cartDetails;

    @PostConstruct
    public void createShoppingCart(){
        shoppingCart = new HashMap<>();
        cartDetails = new HashMap<>();
    }

    public ShoppingCartService() {
    }

    public void addToCart(Product product, int quantity){
        shoppingCart.put(product,quantity);
    }

    public void addToCartDetails(Product product, int quantity) {
        double priceOfProduct = product.getPrice();
        List<Double> quantityAndPriceList = new ArrayList<>(2);
        quantityAndPriceList.add((double) quantity);

        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        double priceOfDesiredAmount = Double.parseDouble(formatter.format(priceOfProduct * quantity));

        quantityAndPriceList.add(priceOfDesiredAmount);
        cartDetails.put(product, quantityAndPriceList);
    }

    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public Map<Product, List<Double>> getCartDetails() {
        return cartDetails;
    }

    public double calculatePrice(){
        double totalPrice = 0;
        for(Map.Entry<Product, Integer> entry : shoppingCart.entrySet()){
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }

    public int getTotalItems(){
        int totalItems = 0;
        for(Map.Entry<Product, Integer> entry: shoppingCart.entrySet()){
            totalItems += entry.getValue();
        }
        return totalItems;
    }

    public void updateShoppingCart(Product product, Integer quantity){
        shoppingCart.replace(product, quantity);
    }
}


