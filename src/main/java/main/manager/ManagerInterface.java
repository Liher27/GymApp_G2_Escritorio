package main.manager;

import java.sql.SQLException;
import java.util.List;

public interface ManagerInterface<T> {

	/**
	 * Gets all objects from the database
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public List<T> getAll() throws Exception;

	/**
	 * Inserts the object to the database
	 * 
	 * @param t
	 * @throws Exception
	 * @throws SQLException
	 */
	public boolean insert(T t) throws Exception;

	/**
	 * Search the object to the database
	 * 
	 * @param t
	 * @throws Exception
	 * @throws SQLException
	 */
	public T getOne(T t) throws Exception;

	/**
	 * Modifies table from the database
	 * 
	 * @param t
	 * @throws Exception
	 * @throws SQLException
	 */
	public void modify(T t) throws Exception;

	/**
	 * Deletes a Row from the database
	 * 
	 * @param t
	 * @throws Exception
	 * @throws SQLException
	 */
	public void delete(T t) throws SQLException, Exception;

}
