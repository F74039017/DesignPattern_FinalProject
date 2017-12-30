package org.imslab.sqlite.command.createTable;

import org.imslab.sqlite.DB;

public class CreateChineseQuestionTableCmd extends CreateQuestionTableCmd {

	public CreateChineseQuestionTableCmd() {
		super("CreateChineseQuestionTableCmd", DB.CHINESE_TABLENAME);
	}
}
