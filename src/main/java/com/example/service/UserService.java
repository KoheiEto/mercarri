package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public void insert(User user) {
		userRepository.insert(user);
	}

	public boolean existByEmail(String email) {
		boolean result = userRepository.existByEmail(email);
		return result;
	}
}
