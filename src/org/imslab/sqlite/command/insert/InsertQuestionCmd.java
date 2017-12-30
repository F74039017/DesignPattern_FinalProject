package org.imslab.sqlite.command.insert;

import java.sql.Statement;

import org.imslab.question.Question;
import org.imslab.sqlite.command.AbstractQuestionCmd;

public abstract class InsertQuestionCmd extends AbstractQuestionCmd {

	/**
	 * Pass tableName parameter to create the question table for the specific subject.
	 * @param cmdName 
	 * @param tableName
	 */
	public InsertQuestionCmd(String cmdName, String tableName, Question question) {
		super(cmdName, tableName);
		addDependentCmd(new InsertCmd(tableName, 
										contentField, question.getContent(),
										lvField, question.getLv(),
										selectFieldA, question.getSa(),
										selectFieldB, question.getSb(),
										selectFieldC, question.getSc(),
										selectFieldD, question.getSd()));
	}

	@Override
	public void exec(Statement statement) throws Exception {
		// use dependent command
	}
		
}
