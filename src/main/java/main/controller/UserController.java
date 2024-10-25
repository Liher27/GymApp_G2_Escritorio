package main.controller;

import main.manager.UserManager;
import main.manager.pojo.User;

public class UserController {

	public boolean changeUser(User userProfile) throws Exception {
		return new UserManager().modify(userProfile);
	}

}
