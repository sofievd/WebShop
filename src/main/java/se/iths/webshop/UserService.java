package se.iths.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        userRepo.save(user);
        return user;
    }

    public boolean emailAlreadyExists (String email) {
        return userRepo.existsById(email);
    }


}
