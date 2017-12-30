package org.imslab.sqlite.command.createTable;

import java.sql.Statement;

import org.imslab.sqlite.command.AbstractQuestionCmd;

public abstract class CreateQuestionTableCmd extends AbstractQuestionCmd {

	/**
	 * Pass tableName parameter to create the question table for the specific subject.
	 * @param cmdName 
	 * @param tableName
	 */
	public CreateQuestionTableCmd(String cmdName, String tableName) {
		super(cmdName, tableName);
		addDependentCmd(new CreateTableCmd(tableName, contentField, lvField, 
						selectFieldA, selectFieldB, selectFieldC, selectFieldD));
	}

	@Override
	public void exec(Statement statement) throws Exception {
		// use dependent command
	}
		
}
