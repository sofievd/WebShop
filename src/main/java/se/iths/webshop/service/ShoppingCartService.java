package se.iths.webshop.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.iths.webshop.dto.CartItem;
import se.iths.webshop.entity.Product;
import se.iths.webshop.util.DecimalFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public List<CartItem> getCartItemsForCheckout() {
        List<CartItem> cartItemList = new ArrayList<>();
        for(Map.Entry<Product, Integer> entry : shoppingCart.entrySet()){
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = DecimalFormatter.formatToTwoDecimalPlaces(product.getPrice());
            double totalPrice = DecimalFormatter.formatToTwoDecimalPlaces(quantity * price);
            CartItem cartItem = new CartItem(product.getName(), price, quantity, totalPrice);
            cartItemList.add(cartItem);
        }
        return cartItemList;
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
        return DecimalFormatter.formatToTwoDecimalPlaces(totalPrice);
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


