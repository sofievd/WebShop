package se.iths.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.webshop.entity.Product;
import se.iths.webshop.service.OrderService;
import se.iths.webshop.service.ShoppingCartService;
import se.iths.webshop.service.UserServiceImpl;

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

    @GetMapping("/shopping-cart")
    public String showShoppingCart(Model model) {
        List<Product> productList = new ArrayList<>();
        List<Integer> quantityList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cartService.getShoppingCart().entrySet()) {
            productList.add(entry.getKey());
            quantityList.add(entry.getValue());
        }
        model.addAttribute("product", productList);
        model.addAttribute("quantity", quantityList);
        model.addAttribute("cart", cartService);
        return "shoppingBasket";
    }

    @GetMapping("/checkout")
    public String processShoppingCart(Model model) {
        List<Product> productList = new ArrayList<>();
        List<Integer> quantityList = new ArrayList<>();

        Map<Product, Integer> shoppingCart = cartService.getShoppingCart();
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            productList.add(entry.getKey());
            quantityList.add(entry.getValue());
        }

        double totalPrice = cartService.calculatePrice();

        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalNumOfArticles", cartService.getTotalItems());
//        model.addAttribute("product", productList);
//        model.addAttribute("quantity", quantityList);
//        model.addAttribute("cart", cartService);
        return "checkout";
    }

    @PostMapping("/payment")
    public String orderConfirmation(@RequestParam("totalPrice") double totalPrice) {

        Map<Product, Integer> shoppingCart = cartService.getShoppingCart();

        System.out.println("Shopping cart: " + shoppingCart);
        orderService.createOrder(shoppingCart, totalPrice);
        return "redirect:/cart/checkout?success";
    }
}
