package main.controller;

import main.manager.UserManager;
import main.manager.pojo.Historic;
import main.manager.pojo.User;

public class UserController {

	public boolean changeUser(User userProfile) throws Exception {
		return new UserManager().modify(userProfile);
	}
	
	public void insertWorkoutHistory(Historic historic,User user) {
		
		try {
			new UserManager().addWorkoutHistroyToBase(historic, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
