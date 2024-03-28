package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.OrderLine;

import java.util.List;


@Repository
public interface OrderLineRepo extends JpaRepository<OrderLine, Integer> {

    List<OrderLine> findByOrder(Order order);
}
