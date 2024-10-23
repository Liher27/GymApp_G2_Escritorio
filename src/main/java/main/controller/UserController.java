package main.controller;

import main.manager.UserManager;
import main.manager.pojo.User;

public class UserController {

	public boolean changeUserPassword(User userProfile) throws Exception {
		return new UserManager().modify(userProfile);
	}

	public User getUserInfo() {
		
		return null;
	}

}
