package org.imslab.sqlite.command.delete;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;

public class DeleteMathQuestionCmd extends DeleteQuestionCmd {

	public DeleteMathQuestionCmd(Question question) {
		super("DeleteMathQuestionCmd", DB.MATH_TABLENAME, question);
	}
	
}

