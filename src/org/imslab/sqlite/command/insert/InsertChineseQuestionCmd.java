package org.imslab.sqlite.command.insert;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;

public class InsertChineseQuestionCmd extends InsertQuestionCmd {

	public InsertChineseQuestionCmd(Question question) {
		super("InsertChineseQuestionCmd", DB.CHINESE_TABLENAME, question);
	}
	
}
