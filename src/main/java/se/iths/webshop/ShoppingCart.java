package se.iths.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.iths.webshop.entity.Product;
import se.iths.webshop.service.ProductService;

import java.util.ArrayList;
import java.util.Map;

@Component
public class ShoppingCart {
    @Autowired
    private ProductService productService ;

    private ArrayList<int[]> basket = new ArrayList<>();

    public ShoppingCart() {
    }

    public ArrayList<int[]> getBasket() {
        return basket;
    }

    public void setBasket(ArrayList<int[]> basket) {
        this.basket = basket;
    }

    public void add(Product product, Integer quantity) {
        int productId = product.getId();
       int[] basketItemPair = {productId, quantity};
       basket.add(basketItemPair);
//        int productId = product.getId();
//        int[] basketItemPair = {productId, quantity};
//        for(int i= 0; i<basket.size(); i++){
//            if(basket.get(i)[0] != productId){
//                basket.add(basketItemPair);
//            }
//            else {
//                update(product, quantity);}
//        }
    }

    public void update(Product product, Integer quantity) {
        int[] updatedItemPair = {product.getId(), quantity};
        for (int i = 0; i < basket.size(); i++) {
            if (basket.get(i)[0] == product.getId()) {
                System.out.println("YAY!");
                basket.remove(i);
                basket.add(updatedItemPair);
            }
        }
    }

    public double calculatePrice(){
        double totalPrice = 0;
        for(int i= 0; i< basket.size(); i++){
            Product product = productService.findProductById(basket.get(i)[0]);
            totalPrice += (product.getPrice())* basket.get(i)[1];
        }
        return totalPrice;
    }

    public int getTotalItems() {
        int totalItems = 0;
        for(int i= 0; i< basket.size(); i++){
            totalItems += basket.get(i)[1];
        }
        return totalItems;
    }
}

