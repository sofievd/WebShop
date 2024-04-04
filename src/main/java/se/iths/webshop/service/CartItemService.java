package se.iths.webshop.service;

import org.springframework.stereotype.Service;
import se.iths.webshop.dto.CartItem;
import se.iths.webshop.entity.OrderLine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>CartItemService</h2>
 * @date 2024-03-28
 */
@Service
public class CartItemService {

    static final List<CartItem> cartItemsList = new ArrayList<>();

    public List<CartItem> getCartItemsList() {
        return cartItemsList;
    }

    public List<CartItem> getCartItemsList(List<OrderLine> orderLineList) {

        for (OrderLine orderLine: orderLineList) {
            CartItem cartItem = new CartItem();
            cartItem.setProductName(orderLine.getProduct().getName());
            cartItem.setPrice(orderLine.getProduct().getPrice());
            cartItem.setQuantity(orderLine.getAmount());
            cartItem.setTotalPrice(orderLine.getProduct().getPrice() * orderLine.getAmount());
            cartItemsList.add(cartItem);
        }
        return cartItemsList;
    }

    public int getTotalNumberOfItemsInCart(List<CartItem> cartItemsList) {
        int totalItemsInCart = 0;
        for (CartItem item: cartItemsList) {
            totalItemsInCart = totalItemsInCart + item.getQuantity();
        }
        return totalItemsInCart;
    }

    public CartItem getCartItemByName(String name, List<CartItem> listOfCartItems) {
        CartItem desiredItem = null;
        for (CartItem item : listOfCartItems) {
            if (item.getProductName().equalsIgnoreCase(name)) {
                desiredItem = item;
            }
        }
        return desiredItem;
    }
}
