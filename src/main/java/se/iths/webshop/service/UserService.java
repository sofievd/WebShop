package se.iths.webshop.service;

import se.iths.webshop.dto.UserDto;
import se.iths.webshop.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>UserService</h2>
 * @date 2024-03-20
 */
public interface UserService {

    void saveUser(UserDto userDto);

    Optional<User> findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
