package se.iths.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.OrderLine;
import se.iths.webshop.entity.Product;
import se.iths.webshop.entity.User;
import se.iths.webshop.repository.OrderRepo;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version <h2></h2>
 * @date 2024-03-26
 */

@Service
public class OrderService {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderLineService orderLineService;

    public void createOrder(Map<Product, Integer> shoppingCart, double totalPrice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();

        System.out.println("User email: " + loggedInUserEmail);
        Order order = new Order();
        order.setDate(LocalDateTime.now());
        System.out.println("Date: " + LocalDateTime.now());
        System.out.println("total price: " + totalPrice);

        Optional<User> optionalUser = userService.findUserByEmail(loggedInUserEmail);
        if (optionalUser.isPresent()) {
            User loggedInUser = optionalUser.get();
            order.setUser(loggedInUser);
            System.out.println("Logged-in user: " + loggedInUser);
            orderRepo.save(order);
        }

        System.out.println("Order: " + order);

        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            OrderLine orderline = new OrderLine();
            orderline.setOrder(order);
            orderline.setProduct(entry.getKey());
            orderline.setAmount(entry.getValue());
            orderLineService.createOrderLine(orderline);
        }
    }
}
