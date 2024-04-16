package se.iths.webshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.OrderLine;
import se.iths.webshop.entity.Product;
import se.iths.webshop.repository.OrderLineRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderLineServiceTest {

    OrderLineService service;
    @Mock
    private OrderLineRepo repo;

    private OrderLine orderline1, orderLine2;
    private Product product1, product2;
    private Order order;


    @BeforeEach
    void setUp(){
        service = new OrderLineService(repo);

        order = new Order(1);

        product1 = new Product("test1", 24.6);
        product2= new Product("test2", 56.9);

        orderline1 = new OrderLine(1, product1, order, 2);
        orderLine2 = new OrderLine(2, product2, order, 5);

    }

    @Test
    void createOrderLine() {
        when(repo.save(orderline1)).thenReturn(orderline1);
        service.createOrderLine(orderline1);

        verify(repo, times(1)).save(orderline1);
    }

    @Test
    void getOrderLinesByOrder() {
        when(repo.findByOrder(order)).thenReturn(List.of(orderline1, orderLine2));
        List<OrderLine> wantedOrders = service.getOrderLinesByOrder(order);

        assertEquals(2, wantedOrders.size());
    }
}