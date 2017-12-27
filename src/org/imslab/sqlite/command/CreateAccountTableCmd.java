package org.imslab.sqlite.command;

import java.sql.Statement;

public class CreateAccountTableCmd extends Command {

	private static String tableName = "Account";
	private static String nameField = "NAME";
	private static String passwordField = "PASSWORD";

	/**
	 * Table name and field is static. All args will be ignored.
	 * @param args
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
