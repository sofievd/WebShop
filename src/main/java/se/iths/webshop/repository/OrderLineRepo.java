package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.OrderLine;

import java.util.List;
/**
 * @author Sofie Van Dingenen, Depinder Kaur
 * @version 1.0
 * <h2> OrderLineRepo </h2></>
 * @date 2024-04-08
 */


@Repository
public interface OrderLineRepo extends JpaRepository<OrderLine, Integer> {

    List<OrderLine> findByOrder(Order order);
}
