package se.iths.webshop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.webshop.dto.CartItem;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.Product;
import se.iths.webshop.entity.User;
import se.iths.webshop.service.EmailService;
import se.iths.webshop.service.OrderService;
import se.iths.webshop.service.ProductService;
import se.iths.webshop.service.ShoppingCartService;
import se.iths.webshop.util.CustomDateFormatter;
import se.iths.webshop.util.DecimalFormatter;

import java.time.LocalDateTime;
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
    private EmailService emailService;

    @Autowired
    private ProductService pService;

    @GetMapping("/shoppingCart")
    public String showShoppingCart(Model model) {
        double totalPrice = cartService.calculatePrice();

        if (cartService.getShoppingCart().isEmpty()) {
            return "redirect:/product/webShop?error";
        }

        model.addAttribute("cart", cartService);
        model.addAttribute("totalPrice", DecimalFormatter.formatToTwoDecimalPlaces(totalPrice));
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
        model.addAttribute("totalCartPrice", DecimalFormatter.formatToTwoDecimalPlaces(totalCartPrice));
        model.addAttribute("totalNumOfArticles", cartService.getTotalItems());
        return "customer/checkout";
    }

    @PostMapping("/payment")
    public String orderConfirmation(@RequestParam("totalCartPrice") double totalCartPrice,
                                    Model model) {

        Map<Product, Integer> shoppingCart = cartService.getShoppingCart();
        if (shoppingCart.isEmpty()) {
            return "redirect:/product/webShop";
        }
        List<CartItem> cartItemList = cartService.getCartItemsForCheckout();
        Order savedOrder = orderService.createOrder(shoppingCart, totalCartPrice);

        User currentUser = savedOrder.getUser();

        emailService.sendOrderConfirmation(currentUser, cartItemList, savedOrder);
        int totalNumOfArticles = cartService.getTotalItems();
        shoppingCart.clear();

        LocalDateTime dateTime = savedOrder.getDate();
        String formattedDateTime = CustomDateFormatter.getFormattedDateTime(dateTime);

        model.addAttribute("formattedDateTime", formattedDateTime);
        model.addAttribute("user", currentUser);
        model.addAttribute("order", savedOrder);
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("totalCartPrice", DecimalFormatter.formatToTwoDecimalPlaces(totalCartPrice));
        model.addAttribute("totalNumOfArticles", totalNumOfArticles);
        return "customer/order-confirmation";
    }

    @PostMapping("/addProductToBasket")
    public String addProductToBasket(@Valid @ModelAttribute("id") int id,
                                     @RequestParam("quantity") String quantity,
                                     BindingResult theBindingResult,
                                     Model model) {

        Product desiredProduct = pService.findProductById(id);

        if (theBindingResult.hasErrors()) {
            model.addAttribute("product", desiredProduct);
            return "customer/choose-quantity-of-product";
        } else {
            int quantityInt = Integer.parseInt(quantity);
            cartService.addToCart(desiredProduct, quantityInt);
            return "redirect:/product/webShop?success";
        }
    }

    @PostMapping("/chooseQuantityOfProduct")
    public String chooseQuantityOfProduct(@RequestParam("id") int id, Model model) {

        Product desiredProduct = pService.findProductById(id);
        model.addAttribute("product", desiredProduct);
        model.addAttribute("quantity", "1");

        return "customer/choose-quantity-of-product";
    }


    @PostMapping("/updateQuantityOfProduct")
    public String updateQuantityOfProduct(@RequestParam("id") int id, Model model) {

        Product desiredProduct = pService.findProductById(id);
        model.addAttribute("product", desiredProduct);
        model.addAttribute("quantity", "1");

        return "customer/choose-quantity-of-product-update";
    }

    @PostMapping("/update-basket")
    public String updateBasket(@Valid @ModelAttribute("id") int id,
                               @Valid @ModelAttribute("quantity") int quantity,
                               BindingResult theBindingResult, Model model) {
        Product desiredProduct = pService.findProductById(id);

        if (theBindingResult.hasErrors()) {
            model.addAttribute("product", desiredProduct);
            return "customer/choose-quantity-of-product-update";
        } else {
            cartService.updateShoppingCart(desiredProduct, quantity);
        }
        return "redirect:/product/webShop?success";
    }

    // add an InitBinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
