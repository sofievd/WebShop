package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Orderline;


@Repository
public interface OrderlineRepo extends JpaRepository<Orderline, Integer> {
}
