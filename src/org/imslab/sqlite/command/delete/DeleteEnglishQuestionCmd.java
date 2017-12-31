package org.imslab.sqlite.command.delete;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;

public class DeleteEnglishQuestionCmd extends DeleteQuestionCmd {

	public DeleteEnglishQuestionCmd(Question question) {
		super("DeleteEnglishQuestionCmd", DB.ENGLISH_TABLENAME, question);
	}
	
}

