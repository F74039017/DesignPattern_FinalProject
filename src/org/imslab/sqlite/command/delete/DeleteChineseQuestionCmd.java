package org.imslab.sqlite.command.delete;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;

public class DeleteChineseQuestionCmd extends DeleteQuestionCmd {

	public DeleteChineseQuestionCmd(Question question) {
		super("DeleteChineseQuestionCmd", DB.CHINESE_TABLENAME, question);
	}
	
}
