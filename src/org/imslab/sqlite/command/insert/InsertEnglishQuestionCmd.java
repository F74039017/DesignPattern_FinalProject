package org.imslab.sqlite.command.insert;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;

public class InsertEnglishQuestionCmd extends InsertQuestionCmd {

	public InsertEnglishQuestionCmd(Question question) {
		super("InsertEnglishQuestionCmd", DB.ENGLISH_TABLENAME, question);
	}
	
}
