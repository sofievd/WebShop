package se.iths.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshop.dto.CartItem;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.OrderLine;
import se.iths.webshop.service.CartItemService;
import se.iths.webshop.service.OrderLineService;
import se.iths.webshop.service.OrderService;
import se.iths.webshop.util.CustomDateFormatter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>OrderController</h2>
 * @date 2024-03-28
 */

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderLineService orderLineService;

    @GetMapping("/{id}")
    public String findOrderById(@PathVariable int id, Model model) {
        Optional<Order> optionalOrder = orderService.findOrderById(id);
        if (optionalOrder.isPresent()) {
            Order desiredOrder = optionalOrder.get();
            double totalCartPrice = desiredOrder.getTotalAmount();
            LocalDateTime dateTime = desiredOrder.getDate();
            String formattedDateTime = CustomDateFormatter.getFormattedDateTime(dateTime);
            
            List<OrderLine> orderlineList = orderLineService.getOrderLinesByOrder(desiredOrder);
            List<CartItem> cartItemsList = cartItemService.getCartItemsList(orderlineList);
            int totalNumOfArticles = cartItemService.getTotalNumberOfItemsInCart(cartItemsList);
            
            model.addAttribute("cartItemsList", cartItemsList);
            model.addAttribute("desiredOrder", desiredOrder);
            model.addAttribute("formattedDateTime", formattedDateTime);
            model.addAttribute("totalCartPrice", totalCartPrice);
            model.addAttribute("totalNumOfArticles", totalNumOfArticles);
            return "admin/order-details";
        } else {
            return "admin/no-order-found";
        }
    }
}
