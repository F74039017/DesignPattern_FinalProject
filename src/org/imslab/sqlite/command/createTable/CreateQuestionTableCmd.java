package org.imslab.sqlite.command.createTable;

import java.sql.Statement;

import org.imslab.sqlite.command.AbstractModQuestionCmd;

public abstract class CreateQuestionTableCmd extends AbstractModQuestionCmd {

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
	public boolean exec(Statement statement) throws Exception {
		// use dependent command
		return true;
	}
		
}
