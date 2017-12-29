package org.imslab.sqlite.command;

import org.imslab.sqlite.DB;

public class CreateEnglishQuestionTableCmd extends CreateQuestionTableCmd {

	public CreateEnglishQuestionTableCmd() {
		super(DB.ENGLISH_TABLENAME);
	}
}
