package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Category;
/**
 * @author Sofie Van Dingenen
 * @version 1.0
 * <h2> CategoryRepo </h2></>
 * @date 2024-04-08
 */

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    public Category findByName(String name);
}
