package org.imslab.sqlite.command.createTable;

import java.sql.Statement;

import org.imslab.sqlite.DB;
import org.imslab.sqlite.command.ModifyCommand;

public class CreateAccountTableCmd extends ModifyCommand {

	public static String tableName = DB.ACCOUNT_TABLENAME;
	public static String nameField = DB.ACCOUNT_NAME;
	public static String passwordField = DB.ACCOUNT_PASSWORD;

	/**
	 * Table name and field is static. All args will be ignored.
	 * @param args  null
	 */
	public CreateAccountTableCmd() {
		super("CreateAccountTable");
		addDependentCmd(new CreateTableCmd(tableName, nameField, passwordField));
	}

	@Override
	public void exec(Statement statement) throws Exception {
		// use dependent command
	}
		
}
