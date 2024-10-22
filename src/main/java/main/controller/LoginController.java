package main.controller;

import java.sql.SQLException;
import java.util.List;

import main.manager.LoginManager;
import main.manager.pojo.User;

public class LoginController implements ControllerInterface {

	private LoginManager loginManager;

	@Override
	public List getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Object t) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object t) {
		// TODO Auto-generated method stub

	}

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
}
