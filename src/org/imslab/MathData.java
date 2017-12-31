package org.imslab;

import org.imslab.question.MathQuestionCmdFactory;
import org.imslab.sqlite.DB;

public class MathData extends SubjectData {

	public MathData(Operand operand) {
		super(new MathQuestionCmdFactory(), DB.MATH_TABLENAME, operand);
	}
	
}