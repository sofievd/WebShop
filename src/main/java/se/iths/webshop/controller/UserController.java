package se.iths.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshop.entity.Order;
import se.iths.webshop.service.OrderService;

import java.util.List;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>UserController</h2>
 * @date 2024-03-28
 */
@Controller
@RequestMapping("/customers")
public class UserController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/allOrders")
    public String allOrders(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();

        List<Order> allOrdersOfUser = orderService.getAllOrdersOfUser(loggedInUserEmail);
        model.addAttribute("ordersList", allOrdersOfUser);
        return "customer/orders-list";
    }

}
