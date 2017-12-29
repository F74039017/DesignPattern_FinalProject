package org.imslab.sqlite.command;

import java.sql.Statement;

import org.imslab.sqlite.DB;

public class CreateAccountTableCmd extends ModifyCommand {

	public static String tableName = DB.ACCOUNT_TABLENAME;
	public static String nameField = DB.ACCOUNT_NAME;
	public static String passwordField = DB.ACCOUNT_PASSWORD;

	/**
	 * Table name and field is static. All args will be ignored.
	 * @param args  null
	 */
	public CreateAccountTableCmd(String... args) {
		super("CreateAccountTable", args);
		addDependentCmd(new CreateTableCmd(tableName, nameField, passwordField));
	}

	@Override
	public void exec(Statement statement) throws Exception {
		// use dependent command
	}
		
}
