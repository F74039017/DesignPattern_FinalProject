package org.imslab.sqlite.command.createTable;

import org.imslab.sqlite.DB;

public class CreateMathQuestionTableCmd extends CreateQuestionTableCmd {

	public CreateMathQuestionTableCmd() {
		super("CreateMathQuestionTableCmd", DB.MATH_TABLENAME);
	}
}
