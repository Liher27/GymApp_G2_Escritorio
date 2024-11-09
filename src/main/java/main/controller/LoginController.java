package main.controller;

import main.manager.LoginManager;
import main.manager.pojo.User;

public class LoginController {

	private LoginManager loginManager;

	public boolean loginUser(String username, String password) throws Exception {
		loginManager = new LoginManager();
		User user = new User();
		user.setName(username);
		user.setPass(password);
		return loginManager.validateUser(user);
	}
}
