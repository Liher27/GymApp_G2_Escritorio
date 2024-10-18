package main.manager;

import java.sql.SQLException;
import java.util.List;

import main.manager.pojo.User;

public class LoginManager implements ManagerInterface<User> {

	@Override
	public List<User> getAll() throws SQLException, Exception {
		return null;
	}

	@Override
	public void insert(User user) throws Exception {

	}

	@Override
	public User getOne(User user) throws Exception {
		return null;
	}

	@Override
	public void modify(User user) throws Exception {

	}

	@Override
	public void delete(User user) throws Exception {

	}

}
