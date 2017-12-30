package org.imslab.sqlite.command.insert;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;

public class InsertMathQuestionCmd extends InsertQuestionCmd {

	public InsertMathQuestionCmd(Question question) {
		super("InsertMathQuestionCmd", DB.MATH_TABLENAME, question);
	}
	
}
