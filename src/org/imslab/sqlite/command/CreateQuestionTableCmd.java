package org.imslab.sqlite.command;

import java.sql.Statement;

public abstract class CreateQuestionTableCmd extends ModifyCommand {

	private static String tableName = null;
	private static String contentField = "CONTENT";
	private static String lvField = "LV";
	private static String selectFieldA = "SA";
	private static String selectFieldB = "SB";
	private static String selectFieldC = "SC";
	private static String selectFieldD = "SD";
	

	/**
	 * Pass tableName parameter to create the question table for the specific subject.
	 * @param args   arg[0] = table name of the subject
	 */
	public CreateQuestionTableCmd(String... args) {
		super("CreateQuestionTableCmd", args);
		if (this.args.size() != 1) {
			System.err.println("CreateQuestionTableCmd need tableName argument");
			System.exit(1);
		}
		tableName = this.args.get(0);
		addDependentCmd(new CreateTableCmd(tableName, contentField, lvField, 
						selectFieldA, selectFieldB, selectFieldC, selectFieldD));
	}

	@Override
	public void exec(Statement statement) throws Exception {
		// use dependent command
	}
		
}
