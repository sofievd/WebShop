package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Order;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query(nativeQuery = true,
            value = "select * from webshop.orders where customer_email=:email order by orders.id desc")
    List<Order> findByUserEmail(String email);
}
