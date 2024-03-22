package se.iths.webshop.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.repository.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
}
