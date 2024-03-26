package se.iths.webshop.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.iths.webshop.entity.Product;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public void addToCart(Product product, Integer quantity){
        shoppingCart.put(product,quantity);
    }

    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
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


