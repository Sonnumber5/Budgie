package com.jackp.business;

import org.springframework.stereotype.Service;

import com.jackp.entity.UserEntity;
import com.jackp.model.UserModel;
import com.jackp.repository.UserRepository;

@Service
public class UserBusinessService implements UserBusinessInterface{
	private final UserRepository userRepository;
	
	public UserBusinessService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String addUser(UserModel user) {
		UserEntity userEntity = new UserEntity(user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword(), user.getEmail());
		String result = userRepository.registerUser(userEntity);
		return result;
	}

	@Override
	public int getUserIdByUsername(String sessionUsername) {
		UserEntity user = userRepository.findByUsername(sessionUsername);
		return user.getId();
	}

}
