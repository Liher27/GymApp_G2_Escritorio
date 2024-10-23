package main.manager;

import java.sql.SQLException;
import java.util.List;

import main.manager.pojo.User;

public class WorkoutManager implements ManagerInterface<User>{

	@Override
	public List<User> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(User t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getOne(User t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modify(User t) throws Exception {
		return false;
	}

	@Override
	public void delete(User t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
