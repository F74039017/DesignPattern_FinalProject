package org.imslab.sqlite.command.update;

import java.sql.Statement;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;
import org.imslab.sqlite.command.AbstractModQuestionCmd;

public abstract class UpdateQuestionCmd extends AbstractModQuestionCmd {

	/**
	 * Pass tableName parameter to create the question table for the specific subject.
	 * @param cmdName 
	 * @param tableName
	 */
	public UpdateQuestionCmd(String cmdName, String tableName, Question question) {
		super(cmdName, tableName);
		if (question.getId() == -1 || question.getId()<0) {
			new Exception(this.name + ": empty or negative question id").printStackTrace();
			System.exit(1);
		}
		addDependentCmd(new UpdateCmd(tableName,
										contentField, question.getContent(),
										lvField, question.getLv(),
										selectFieldA, question.getSa(),
										selectFieldB, question.getSb(),
										selectFieldC, question.getSc(),
										selectFieldD, question.getSd(),
										"--",
										DB.PRIMARY_FIELD, String.valueOf(question.getId()))); // where clause
	}

	@Override
	public boolean exec(Statement statement) throws Exception {
		// use dependent command
		return true;
	}
		
}

