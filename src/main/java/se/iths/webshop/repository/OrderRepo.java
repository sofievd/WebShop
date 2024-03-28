package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
}
