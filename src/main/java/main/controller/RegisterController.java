package main.controller;

import main.manager.RegisterManager;
import main.manager.pojo.User;

public class RegisterController {

	public boolean registerUser(User user) throws Exception {
		return new RegisterManager().insert(user);
	}

}
