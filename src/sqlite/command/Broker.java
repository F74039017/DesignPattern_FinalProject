package sqlite.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sqlite.DB;

public class Broker {

	protected Connection connection = null;
	protected List<Command> commandList = null;
	
	public Broker() {
		commandList = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(DB.CONN_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void addCommand(Command cmd) {
		commandList.add(cmd);
	}
	
	/**
	 * Try to execute all commands in the commandList.
	 * Clear the commandList no matter success or not.
	 * @throws Exception
	 */
	public void execMod() throws Exception {
		try {
			for (Command command : commandList) {
				if (connection == null) {
					System.out.println("Null Connection");
				}
				else { 
					System.out.println("Cool");
				}
				command.rootExec(connection);
			}			
		} catch (Exception e) {
			throw e;
		} finally {
			// clear list after execute
			clearCommandList();
		}
	}
	
	public ResultSet execQuery(String sql) throws Exception {
		if (checkConn()) {
			Statement statement = connection.createStatement();
			return statement.executeQuery(sql);
		}
		else {
			throw new Exception("Not Connected to database yet.");
		}
	}
	
	/**
	 * Check if the database was connected already.
	 * @return true if connection was connected.
	 */
	public boolean checkConn() {
		return connection==null? false: true; 
	}
	
	public void clearCommandList() {
		commandList.clear();
	}
	
}
