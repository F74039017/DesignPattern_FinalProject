package org.imslab.sqlite.command.select;

import java.util.List;

import org.imslab.sqlite.DB;

public class SelectChineseQuestionCmd extends SelectQuestionCmd {

	public SelectChineseQuestionCmd(List<String> level) {
		super("SelectChineseQuestionCmd", DB.CHINESE_TABLENAME, level);
	}

}
