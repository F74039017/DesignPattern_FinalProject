package org.imslab.sqlite.command.select;

import java.util.List;

import org.imslab.sqlite.DB;

public class SelectMathQuestionCmd extends SelectQuestionCmd {

	public SelectMathQuestionCmd(List<String> level) {
		super("SelectMathQuestionCmd", DB.MATH_TABLENAME, level);
	}
	
}
