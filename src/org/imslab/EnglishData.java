package org.imslab;

import org.imslab.question.EnglishQuestionCmdFactory;
import org.imslab.sqlite.DB;

public class EnglishData extends SubjectData {

	public EnglishData(Operand operand) {
		super(new EnglishQuestionCmdFactory(), DB.ENGLISH_TABLENAME, operand);
	}
	
}
