package org.imslab.sqlite.command;

import org.imslab.sqlite.DB;

public class CreateChineseQuestionTableCmd extends CreateQuestionTableCmd {

	public CreateChineseQuestionTableCmd() {
		super(DB.CHINESE_TABLENAME);
	}
}
