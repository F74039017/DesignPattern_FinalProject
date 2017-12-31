package org.imslab;

import org.imslab.question.ChineseQuestionCmdFactory;
import org.imslab.sqlite.DB;

public class ChineseData extends SubjectData {

	public ChineseData(Operand operand) {
		super(new ChineseQuestionCmdFactory(), DB.CHINESE_TABLENAME, operand);
	}
	
}
