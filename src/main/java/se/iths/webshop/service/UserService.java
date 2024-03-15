package se.iths.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.webshop.repository.model.User;
import se.iths.webshop.repository.UserRepository;
import se.iths.webshop.util.PasswordEncryptor;

import java.util.Optional;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>UserService</h2>
 * @date 2024-03-14
 */

@Service
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService (UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(String email, String password, String firstName, String lastName) {
        User user = new User(email, password, firstName, lastName);
        String hashPassword = PasswordEncryptor.hashPassword(password);
        user.setPassword(hashPassword);
        userRepo.save(user);
        return user;
    }

    public boolean emailAlreadyExists(String email) {
        return userRepo.existsById(email);
    }

    public boolean validateLogin (String email, String password) {

        boolean result = false;
        Optional<User> opUser = userRepo.findById(email);

        if (opUser.isPresent()) {
            String savedPassword = opUser.get().getPassword();
            return PasswordEncryptor.checkPassword(password, savedPassword);
        }

        return result;
    }
}
