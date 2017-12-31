package org.imslab.sqlite.command.update;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;

public class UpdateMathQuestionCmd extends UpdateQuestionCmd {

	public UpdateMathQuestionCmd(Question question) {
		super("UpdateMathQuestionCmd", DB.MATH_TABLENAME, question);
	}
	
}
