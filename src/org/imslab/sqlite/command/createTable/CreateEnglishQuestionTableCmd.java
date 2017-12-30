package org.imslab.sqlite.command.createTable;

import org.imslab.sqlite.DB;

public class CreateEnglishQuestionTableCmd extends CreateQuestionTableCmd {

	public CreateEnglishQuestionTableCmd() {
		super("CreateEnglishQuestionTableCmd", DB.ENGLISH_TABLENAME);
	}
}
