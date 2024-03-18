package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.webshop.repository.model.Role;

import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version <h2></h2>
 * @date 2024-03-18
 */
public interface RoleRepo extends JpaRepository<Role, Integer> {

    Role findRoleByName(String name);
}
