package se.iths.webshop.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.repository.model.Orderline;

@Repository
public interface OrderlineRepo extends JpaRepository<Orderline, Integer> {
}
