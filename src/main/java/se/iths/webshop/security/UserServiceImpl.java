package se.iths.webshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.webshop.controller.model.WebUser;
import se.iths.webshop.repository.RoleRepo;
import se.iths.webshop.repository.UserRepository;
import se.iths.webshop.repository.model.Role;
import se.iths.webshop.repository.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;

	private RoleRepo roleRepo;

	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepo, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// check the database if the user already exists
		return userRepo.findByEmail(email);
	}

	@Override
	public void save(WebUser webUser) {
		User user = new User();

		// assign user details to the user object
		user.setPassword(passwordEncoder.encode(webUser.getPassword()));
		user.setFirstName(webUser.getFirstName());
		user.setLastName(webUser.getLastName());
		user.setEmail(webUser.getEmail());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleRepo.findRoleByName("ROLE_USER")));

		// save user in the database
		userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> optionalUser = userRepo.findByEmail(email);

		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		User user = optionalUser.get();
		Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				authorities);
	}

	private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role tempRole : roles) {
			SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
			authorities.add(tempAuthority);
		}

		return authorities;
	}

}
