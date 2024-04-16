package se.iths.webshop.service;

import org.springframework.stereotype.Service;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.OrderLine;
import se.iths.webshop.repository.OrderLineRepo;

import java.util.List;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>OrderLineService</h2>
 * @date 2024-03-26
 */

@Service
public class OrderLineService {
    private OrderLineRepo orderLineRepo;

    public OrderLineService(OrderLineRepo orderLineRepo) {
        this.orderLineRepo = orderLineRepo;
    }

    public void createOrderLine(OrderLine orderLine) {
        orderLineRepo.save(orderLine);
    }

    public List<OrderLine> getOrderLinesByOrder(Order order) {
        return orderLineRepo.findByOrder(order);
    }
}
