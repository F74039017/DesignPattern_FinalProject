package org.imslab.sqlite.command.select;

import java.util.List;

import org.imslab.sqlite.DB;

public class SelectEnglishQuestionCmd extends SelectQuestionCmd {

	public SelectEnglishQuestionCmd(List<String> level) {
		super("SelectEnglishQuestionCmd", DB.ENGLISH_TABLENAME, level);
	}
	
}
