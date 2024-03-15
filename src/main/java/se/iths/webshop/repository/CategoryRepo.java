package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    public Category findByName(String name);
}
