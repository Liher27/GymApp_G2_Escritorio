package main.controller;

import main.manager.UserManager;
import main.manager.pojo.Historic;
import main.manager.pojo.User;

public class UserController {

	public boolean changeUser(User userProfile) throws Exception {
		return new UserManager().modify(userProfile);
	}

	public void insertWorkoutHistory(Historic historic, User user) throws Exception {
		new UserManager().addWorkoutHistroyToBase(historic, user);
	}

	public boolean modify(User user) throws Exception {
		return new UserManager().modify(user);
	}
}
