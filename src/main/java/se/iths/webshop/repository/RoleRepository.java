package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.Role;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>RoleRepository</h2>
 * @date 2024-03-20
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
