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
		try {
			String result = userRepository.registerUser(userEntity);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getUserIdByUsername(String sessionUsername) {
		try {
			UserEntity user = userRepository.findByUsername(sessionUsername);
			return user.getId();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public UserModel getUserByUsername(String sessionUsername) {
		try {
			UserEntity entity = userRepository.findByUsername(sessionUsername);
			return new UserModel(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getUserName(), entity.getPassword(), entity.getEmail());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
