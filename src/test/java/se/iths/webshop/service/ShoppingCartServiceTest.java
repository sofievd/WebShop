package se.iths.webshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.webshop.dto.CartItem;
import se.iths.webshop.entity.Category;
import se.iths.webshop.entity.Product;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>ShoppingCartServiceTest</h2>
 * @date 2024-04-09
 */
class ShoppingCartServiceTest {

    private ShoppingCartService cartService;
    private Category category1, category2;
    private Product product1, product2, product3;

    @BeforeEach
    public void setup() {
        cartService = new ShoppingCartService();

        category1 = new Category("test category 1");
        category2 = new Category("test category 2");
        product1 = new Product(1, "test product 1", 30.2, category1, "some random description", "test brand");
        product2 = new Product(2, "test product 2", 44.5, category1, "some random description", "test brand");
        product3 = new Product(3, "test product 3", 18.0, category2, "some random description", "test brand");

        cartService.addToCart(product1, 3);
        cartService.addToCart(product2, 5);
    }

    @Test
    void addToCart() {
        assertEquals(2, cartService.getShoppingCart().size());
        assertTrue(cartService.getShoppingCart().containsKey(product1));
        assertFalse(cartService.getShoppingCart().containsKey(product3));
    }

    @Test
    void getCartItemsForCheckout() {
        List<CartItem> cartItemList = cartService.getCartItemsForCheckout();
        assertEquals(2, cartItemList.size());
        assertEquals("test product 1", cartItemList.get(1).getProductName());
        assertNotEquals(4, cartItemList.get(0).getQuantity());
    }

    @Test
    void getShoppingCart() {
        assertTrue(cartService.getShoppingCart().containsKey(product1));
        assertTrue(cartService.getShoppingCart().containsKey(product2));
        assertFalse(cartService.getShoppingCart().containsKey(product3));

        cartService.getShoppingCart().put(product3, 8);
        assertEquals(3, cartService.getShoppingCart().size());
    }

    @Test
    void calculatePrice() {
        double totalPrice = cartService.calculatePrice();
        assertEquals(313.10, totalPrice);

        cartService.getShoppingCart().put(product3, 1);
        totalPrice = cartService.calculatePrice();
        assertEquals(331.1, totalPrice);
    }

    @Test
    void getTotalItems() {
        int numOfItems = cartService.getTotalItems();
        assertEquals(8, numOfItems);

        cartService.getShoppingCart().put(product3, 4);
        numOfItems = cartService.getTotalItems();
        assertEquals(12, numOfItems);
    }

    @Test
    void removeItemFromShoppingCart() {
        cartService.removeItemFromShoppingCart("test product 1");
        Map<Product, Integer> shoppingCart = cartService.getShoppingCart();
        assertEquals(1, shoppingCart.size());
        assertTrue(shoppingCart.containsKey(product2));
        assertFalse(shoppingCart.containsKey(product1));

        cartService.removeItemFromShoppingCart("test product 2");
        shoppingCart = cartService.getShoppingCart();
        assertEquals(0, shoppingCart.size());
    }

    @Test
    void updateShoppingCart() {
        cartService.updateShoppingCart(product1, 2);
        assertEquals(7, cartService.getTotalItems());

        cartService.updateShoppingCart(product2, 1);
        assertEquals(3, cartService.getTotalItems());
    }
}