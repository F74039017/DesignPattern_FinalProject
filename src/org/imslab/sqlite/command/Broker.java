package org.imslab.sqlite.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.management.Query;

import org.imslab.sqlite.DB;

public class Broker {

	protected Connection connection = null;
	protected List<ModifyCommand> commandList = null;
	
	public Broker() {
		commandList = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(DB.CONN_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void addCommand(ModifyCommand cmd) {
		commandList.add(cmd);
	}
	
	/**
	 * Try to execute all commands in the commandList.
	 * Clear the commandList no matter success or not.
	 * @throws Exception
	 */
	public void execMod() throws Exception {
		try {
			for (ModifyCommand command : commandList) {
				command.rootExec(connection);
			}			
		} catch (Exception e) {
			throw e;
		} finally {
			// clear list after execute
			clearCommandList();
		}
	}
	
	/**
	 * Query command wrapper
	 * @param command
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> execQuery(QueryCommand command) throws Exception {
		try {
			List<HashMap<String, String>> ret = command.query(connection);
			return ret;
		} catch (Exception e) {
			throw e;
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
