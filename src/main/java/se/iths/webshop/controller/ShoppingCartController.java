package se.iths.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.webshop.dto.CartItem;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.Product;
import se.iths.webshop.entity.User;
import se.iths.webshop.service.CartItemService;
import se.iths.webshop.service.EmailService;
import se.iths.webshop.service.OrderService;
import se.iths.webshop.service.ShoppingCartService;
import se.iths.webshop.util.CustomDateFormatter;
import se.iths.webshop.util.DecimalFormatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>ShoppingCartController</h2>
 * @date 2024-03-26
 */
@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/shoppingCart")
    public String showShoppingCart(Model model) {
        List<Product> productList = new ArrayList<>();
        List<Integer> quantityList = new ArrayList<>();

        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : cartService.getShoppingCart().entrySet()) {
            productList.add(entry.getKey());
            quantityList.add(entry.getValue());
            double productPrice = entry.getKey().getPrice() * entry.getValue();
            totalPrice = totalPrice + productPrice;
        }
        totalPrice = DecimalFormatter.formatToTwoDecimalPlaces(totalPrice);

        if (productList.isEmpty()) {
            return "redirect:/product/webShop?error";
        }

        model.addAttribute("product", productList);
        model.addAttribute("quantity", quantityList);
        model.addAttribute("cart", cartService);
        model.addAttribute("totalPrice", totalPrice);
        return "customer/shopping-basket";
    }

    @GetMapping("/remove/{productName}")
    public String showUpdatedCart(@PathVariable("productName") String productName) {
        cartService.removeItemFromShoppingCart(productName);
        return "redirect:/cart/shoppingCart";
    }

    @GetMapping("/checkout")
    public String processShoppingCart(Model model) {
        List<CartItem> cartItemList = cartService.getCartItemsForCheckout();

        double totalCartPrice = cartService.calculatePrice();

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("totalCartPrice", totalCartPrice);
        model.addAttribute("totalNumOfArticles", cartService.getTotalItems());
        return "customer/checkout";
    }

    @PostMapping("/payment")
    public String orderConfirmation(@RequestParam("totalCartPrice") double totalCartPrice,
                                    Model model) {

        Map<Product, Integer> shoppingCart = cartService.getShoppingCart();
        List<CartItem> cartItemList = cartService.getCartItemsForCheckout();
        Order savedOrder = orderService.createOrder(shoppingCart, totalCartPrice);

        User currentUser = savedOrder.getUser();

        emailService.sendOrderConfirmation(currentUser, cartItemList, savedOrder);

        LocalDateTime dateTime = savedOrder.getDate();
        String formattedDateTime = CustomDateFormatter.getFormattedDateTime(dateTime);

        model.addAttribute("formattedDateTime", formattedDateTime);
        model.addAttribute("user", currentUser);
        model.addAttribute("order", savedOrder);
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("totalCartPrice", totalCartPrice);
        model.addAttribute("totalNumOfArticles", cartService.getTotalItems());
        return "customer/order-confirmation";
    }
}
