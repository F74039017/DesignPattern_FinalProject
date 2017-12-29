package org.imslab.sqlite.command;

import org.imslab.sqlite.DB;

public class CreateMathQuestionTableCmd extends CreateQuestionTableCmd {

	public CreateMathQuestionTableCmd() {
		super(DB.MATH_TABLENAME);
	}
}
