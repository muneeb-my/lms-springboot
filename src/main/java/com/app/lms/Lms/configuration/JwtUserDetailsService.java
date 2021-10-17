package com.app.lms.Lms.configuration;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.lms.Lms.Repository.signUpRepository;
import com.app.lms.Lms.model.signUpModel;
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private signUpRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		signUpModel user = userRepository.findByUsername(username);
		if (user != null) {
			return new User(username, user.getPassword(), new ArrayList<>());
			} else {

				throw new UsernameNotFoundException("User not found with username: " + username);
			}

		}
	}

