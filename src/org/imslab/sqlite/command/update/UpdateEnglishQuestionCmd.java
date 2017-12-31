package org.imslab.sqlite.command.update;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;

public class UpdateEnglishQuestionCmd extends UpdateQuestionCmd {

	public UpdateEnglishQuestionCmd(Question question) {
		super("UpdateEnglishQuestionCmd", DB.ENGLISH_TABLENAME, question);
	}
	
}
