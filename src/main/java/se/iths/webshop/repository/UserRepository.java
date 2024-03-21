package se.iths.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.entity.User;

import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>UserRepository</h2>
 * @date 2024-03-14
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String email);
}
