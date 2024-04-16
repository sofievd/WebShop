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

    public List<CartItem> getCartItemsList(List<OrderLine> orderLineList) {

        List<CartItem> cartItemsList = new ArrayList<>();
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
}
