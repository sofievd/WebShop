package se.iths.webshop.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.iths.webshop.entity.Role;
import se.iths.webshop.entity.User;
import se.iths.webshop.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>CustomUserDetailsService</h2>
 * @date 2024-03-20
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> opUser = userRepo.findById(email);

        if (opUser.isPresent()) {
            return new org.springframework.security.core.userdetails.User(opUser.get().getEmail(),
                    opUser.get().getPassword(), mapRolesToAuthorities(opUser.get().getRoles()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password !!");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles
                = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}

