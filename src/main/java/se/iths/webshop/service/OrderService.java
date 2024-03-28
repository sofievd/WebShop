package se.iths.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.OrderLine;
import se.iths.webshop.entity.Product;
import se.iths.webshop.entity.User;
import se.iths.webshop.repository.OrderRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>OrderService</h2>
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

    @Transactional
    public Order createOrder(Map<Product, Integer> shoppingCart, double totalPrice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();

        Order order = new Order();
        Optional<User> optionalUser = userService.findUserByEmail(loggedInUserEmail);
        if (optionalUser.isPresent()) {
            User loggedInUser = optionalUser.get();

            order.setUser(loggedInUser);
            order.setDate(LocalDateTime.now());
            order.setTotalAmount(totalPrice);
            orderRepo.save(order);

            for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
                OrderLine orderline = new OrderLine();
                orderline.setOrder(order);
                orderline.setProduct(entry.getKey());
                orderline.setAmount(entry.getValue());
                orderLineService.createOrderLine(orderline);
            }
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Optional<Order> findOrderById(int id) {
        return orderRepo.findById(id);
    }
}
