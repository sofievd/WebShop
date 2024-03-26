package se.iths.webshop.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.iths.webshop.ShoppingCart;
import se.iths.webshop.entity.Product;

import java.util.ArrayList;


@Service
@SessionScope
public class ShoppingCartService {
    ShoppingCart shoppingCart;
    @PostConstruct
    public void createBasket(){
        shoppingCart= new ShoppingCart();
    }
    public ShoppingCartService() {
    }


    public void addToCart(Product product, Integer quantity){

        shoppingCart.add(product, quantity);
    }
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingcart(ShoppingCart shoppingcart) {
        this.shoppingCart = shoppingcart;
    }
    public double calculatePrice(){
        return shoppingCart.calculatePrice();
    }

    public int getTotalItems(){
       return shoppingCart.getTotalItems();
    }

    public void updateShoppingCart(Product product, Integer quantity){
        shoppingCart.update(product, quantity);
    }
}
