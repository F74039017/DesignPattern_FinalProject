package org.imslab.sqlite.command.select;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imslab.sqlite.DB;
import org.imslab.sqlite.command.QueryCommand;

public class SelectQuestionCmd extends QueryCommand {

	protected String tableName = null;
	protected List<String> level = null;
	protected String lvField = DB.QUESTION_LV;

	/**
	 * Pass tableName parameter to create the question table for the specific subject.
	 * @param cmdName 
	 * @param tableName
	 */
	public SelectQuestionCmd(String cmdName, String tableName, List<String> level) {
		super(cmdName, new String[0]);
		this.tableName = tableName;
		this.level = level;
	}
	
	@Override
	public ResultSet exec(Statement statement) throws Exception {
		List<String> paramList = new ArrayList<>();
		
		// select all columns
		paramList.add(tableName);
		paramList.add("--");
		
		// Where clause
		for (String lvString : level) {
			int lv = Integer.parseInt(lvString);
			if (lv<DB.QUESION_LV_MIN || lv>DB.QUESION_LV_MAX) {
				new Exception("Wrong element in level list.").printStackTrace();
			}
			paramList.add(DB.QUESTION_LV);
			paramList.add(lvString);
		}
		
		return (new SelectCmd(paramList.stream().toArray(String[]::new))).exec(statement);
	}

}
