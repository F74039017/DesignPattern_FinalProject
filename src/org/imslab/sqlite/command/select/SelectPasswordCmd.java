package org.imslab.sqlite.command.select;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imslab.sqlite.DB;
import org.imslab.sqlite.command.QueryCommand;

public class SelectPasswordCmd extends QueryCommand {

	protected String tableName = DB.ACCOUNT_TABLENAME;	
	protected String passwordField = DB.ACCOUNT_PASSWORD;
	protected String usernameField = DB.ACCOUNT_USERNAME;
	
	protected String userName;

	/**
	 * Give user name and query the password's md5 code.
	 * @param userName 
	 */
	public SelectPasswordCmd(String userName) {
		super("SelectPassword", new String[0]);
		this.userName = userName;
	}
	
	@Override
	public ResultSet exec(Statement statement) throws Exception {
		List<String> paramList = new ArrayList<>();
		
		// select all columns
		paramList.add(tableName);
		paramList.add(passwordField); // get password
		paramList.add("--");
		paramList.add(usernameField); // where userName is matched
		paramList.add(userName);
		
		return (new SelectCmd(paramList.stream().toArray(String[]::new))).exec(statement);
	}

}
