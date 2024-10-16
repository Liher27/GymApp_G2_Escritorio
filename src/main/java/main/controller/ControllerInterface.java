package main.controller;

import java.sql.SQLException;
import java.util.List;

public interface ControllerInterface<T> {

	/**
	 * Gets all objects from the database
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public List<T> getAll() throws SQLException, Exception;

	/**
	 * Inserts the object to the database
	 * 
	 * @param t
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public void insert(T t) throws SQLException, Exception;

	/**
	 * Updates the object to the database
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * Deletes the object to the database
	 * 
	 * @param t
	 */
	public void delete(T t);

	
}
