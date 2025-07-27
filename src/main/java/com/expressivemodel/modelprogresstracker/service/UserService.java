package com.expressivemodel.modelprogresstracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expressivemodel.modelprogresstracker.model.User;
import com.expressivemodel.modelprogresstracker.repository.UserRepository;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	public User deleteUser(String username) {
		User user = (User) userRepository.findByUsername(username).orElse(null);
		if(user!=null) {
			userRepository.delete(user);
		}
		return user;
	}
}
