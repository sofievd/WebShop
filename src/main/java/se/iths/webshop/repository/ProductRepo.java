package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Category;
import se.iths.webshop.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);

    List<Product> findAllByCategory(Category category);

    List<Product> findByNameAndCategoryAndBrand(String name, Category category, String brand);
}
