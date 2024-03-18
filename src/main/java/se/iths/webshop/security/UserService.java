package se.iths.webshop.security;


import org.springframework.security.core.userdetails.UserDetailsService;
import se.iths.webshop.controller.model.WebUser;
import se.iths.webshop.repository.model.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {

	Optional<User> findByEmail(String email);

	void save(WebUser webUser);

}
