package org.imslab.sqlite.command.update;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;

public class UpdateChineseQuestionCmd extends UpdateQuestionCmd {

	public UpdateChineseQuestionCmd(Question question) {
		super("UpdateChineseQuestionCmd", DB.CHINESE_TABLENAME, question);
	}
	
}