package main.controller;

import main.manager.RegisterManager;
import main.manager.pojo.User;

public class RegisterController extends SessionController {

	public boolean registerUser(User user) throws Exception {
		return new RegisterManager().insert(user);
	}

}
