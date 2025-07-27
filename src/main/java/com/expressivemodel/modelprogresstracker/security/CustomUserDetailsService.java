package com.expressivemodel.modelprogresstracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.expressivemodel.modelprogresstracker.model.User;
import com.expressivemodel.modelprogresstracker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
    	try {
    	user = (User) userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username+ "User not found"));
        } catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
    }
}
