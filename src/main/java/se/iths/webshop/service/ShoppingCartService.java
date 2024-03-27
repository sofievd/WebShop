package se.iths.webshop.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.iths.webshop.ShoppingCart;
import se.iths.webshop.ShoppingCartMap;
import se.iths.webshop.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
@SessionScope
public class ShoppingCartService {
    @Autowired
    private ProductService productService;
    private Map<Product, Integer> shoppingCart;

    @PostConstruct
    public void createBasket() {
        shoppingCart = new HashMap<>();
    }

    public ShoppingCartService() {
    }


    public void addToCart(Product product, Integer quantity) {
   /*     int productId = product.getId();
        int[] basketItemPair = {productId, quantity};
        if (!shoppingCart.isEmpty()) {
            for (int i = 0; i < shoppingCart.size(); i++) {
                if (shoppingCart.get(i)[0] == productId) {
                    int newQuantity = quantity + shoppingCart.get(i)[1];
                    updateShoppingCart(product, newQuantity);
                    break;
                } else {
                    shoppingCart.add(basketItemPair);
                    break;
                }

            }
        } else {
            shoppingCart.add(basketItemPair);
        }*/

        if (!shoppingCart.isEmpty()) {
            for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
                if (entry.getKey().getId() == product.getId()) {
                    int newQuantity = quantity + entry.getValue();
                    updateShoppingCart(product, newQuantity);
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

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double calculatePrice() {
       /* double totalPrice = 0;
        for (int i = 0; i < shoppingCart.size(); i++) {
            Product product = productService.findProductById(shoppingCart.get(i)[0]);
            totalPrice += (product.getPrice()) * shoppingCart.get(i)[1];
        }
        return totalPrice;*/

        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            totalPrice += (entry.getKey().getPrice()) * entry.getValue();
        }
        return totalPrice;
    }

    public int getTotalItems() {
       /* int totalItems = 0;
        for (int i = 0; i < shoppingCart.size(); i++) {
            totalItems += shoppingCart.get(i)[1];
        }
        return totalItems;*/
        int totalItems = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            totalItems += entry.getValue();
        }
        return totalItems;
    }


    public void updateShoppingCart(Product product, Integer quantity) {
      /*  int[] updatedItemPair = {product.getId(), quantity};
        int wanToremoveIndex = 0;
        for (int i = 0; i < shoppingCart.size(); i++) {
            if (shoppingCart.get(i)[0] == product.getId()) {
                System.out.println("YAY!");
                wanToremoveIndex = i;
            }
        }
        shoppingCart.remove(wanToremoveIndex);


        shoppingCart.add(updatedItemPair); */
        Product productToUpdate = null;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            if (entry.getKey().getId() == product.getId()) {
                System.out.println("YAY!");
                productToUpdate = entry.getKey();
            }
        }
        shoppingCart.replace(productToUpdate, quantity);
    }

}
