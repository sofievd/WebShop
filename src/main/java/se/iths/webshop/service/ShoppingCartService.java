package se.iths.webshop.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.iths.webshop.ShoppingCart;
import se.iths.webshop.ShoppingCartMap;
import se.iths.webshop.entity.Product;


@Service
public class ShoppingCartService {
    ShoppingCartMap shoppingCart;

    @PostConstruct
    public void createBasket() {
        shoppingCart = new ShoppingCartMap();
    }

    public ShoppingCartService() {
    }


    public void addToCart(Product product, Integer quantity) {
        shoppingCart.add(product,quantity);
    }

    public ShoppingCartMap getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingcart(ShoppingCartMap shoppingcart) {
        this.shoppingCart = shoppingcart;
    }

    public double calculatePrice() {
        return shoppingCart.calculatePrice();
    }

    public int getTotalItems() {
        return shoppingCart.getTotalItems();
    }

    public void updateShoppingCart(Product product, Integer quantity) {
        shoppingCart.update(product, quantity);
    }
}
