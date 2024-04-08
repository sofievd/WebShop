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

    @PostConstruct
    public void createShoppingCart(){
        shoppingCart = new HashMap<>();
    }

    public ShoppingCartService() {
    }

    public void addToCart(Product product, int quantity){
        if(!shoppingCart.isEmpty()){
            for(Map.Entry<Product, Integer> entry : shoppingCart.entrySet()){
                if(entry.getKey().getId() == product.getId()){
                    int newQuantity = quantity+ entry.getValue();
                    updateShoppingCart(product, newQuantity);
                } else {
                    shoppingCart.put(product, quantity);
                }
                break;
            }
        }else{
        shoppingCart.put(product,quantity);
        }
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

    public void removeItemFromShoppingCart(String productName){
        Product productToBeRemoved = null;
        for(Map.Entry<Product, Integer> entry: shoppingCart.entrySet()){
            if (entry.getKey().getName().equalsIgnoreCase(productName)) {
                productToBeRemoved = entry.getKey();
            }
        }
        shoppingCart.remove(productToBeRemoved);
    }

    public void updateShoppingCart(Product product, Integer quantity){
        Product productToUpdate = product;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()){
            if(entry.getKey().getId() == product.getId()){
                productToUpdate = entry.getKey();
            }
        }
        shoppingCart.replace(productToUpdate, quantity);
    }
}


