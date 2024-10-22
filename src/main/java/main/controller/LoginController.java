package main.controller;

import java.sql.SQLException;
import java.util.List;

import main.manager.LoginManager;
import main.manager.pojo.User;

public class LoginController implements ControllerInterface<User> {

	private LoginManager loginManager;

	public boolean loginUser(String username, String password) throws Exception {
		loginManager = new LoginManager();
		try {
			User user = new User();
			user.setName(username);
			user.setPassword(password);
			return loginManager.validateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void insert(User t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}
}
