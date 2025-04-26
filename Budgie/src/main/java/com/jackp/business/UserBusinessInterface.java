package com.jackp.business;

import com.jackp.entity.UserEntity;
import com.jackp.model.UserModel;

public interface UserBusinessInterface {

	public String addUser(UserModel user);
	public int getUserIdByUsername(String sessionUsername);
	public UserModel getUserByUsername(String sessionUsername);
}
