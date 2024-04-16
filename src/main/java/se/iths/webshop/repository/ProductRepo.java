package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Category;
import se.iths.webshop.entity.Product;

import java.util.List;
/**
 * @author Sofie Van Dingenen, Depinder Kaur
 * @version 1.0
 * <h2> ProductRepo </h2></>
 * @date 2024-04-08
 */

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByName(String name);

    List<Product> findAllByCategory(Category category);

    List<Product> findByNameAndCategoryAndBrand(String name, Category category, String brand);
}
