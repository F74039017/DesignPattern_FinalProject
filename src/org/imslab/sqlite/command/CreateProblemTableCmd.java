package org.imslab.sqlite.command;

import java.sql.Statement;

public class CreateProblemTableCmd extends ModifyCommand {

	private static String tableName = "Problem";
	private static String contentField = "CONTENT";
	private static String lvField = "LV";
	private static String selectFieldA = "SA";
	private static String selectFieldB = "SB";
	private static String selectFieldC = "SC";
	private static String selectFieldD = "SD";
	

	/**
	 * Table name and field is static. All args will be ignored.
	 * @param args
	 */
	public CreateProblemTableCmd(String... args) {
		super("CreateProblemTable", args);
		addDependentCmd(new CreateTableCmd(tableName, contentField, lvField, 
						selectFieldA, selectFieldB, selectFieldC, selectFieldD));
	}

	@Override
	public void exec(Statement statement) throws Exception {
		// use dependent command
	}
		
}
