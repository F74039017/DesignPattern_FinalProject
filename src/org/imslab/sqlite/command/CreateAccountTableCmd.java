package org.imslab.sqlite.command;

import java.sql.Statement;

public class CreateAccountTableCmd extends ModifyCommand {

	public static String tableName = "Account";
	public static String nameField = "NAME";
	public static String passwordField = "PASSWORD";

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
