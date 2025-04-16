package com.jackp.business;

import com.jackp.model.UserModel;

public interface UserBusinessInterface {

	public String addUser(UserModel user);
	public int getUserIdByUsername(String sessionUsername);
}
