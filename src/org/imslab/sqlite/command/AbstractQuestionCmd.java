package org.imslab.sqlite.command;

import java.sql.Statement;

import org.imslab.sqlite.DB;

/**
 * Subclass invokes super(cmdName, tableName) to set the tableName and appendCommand() to achieve the goal.  
 */
public abstract class AbstractQuestionCmd extends ModifyCommand {

	protected String tableName = null;
	protected String contentField = DB.QUESION_CONTENT;
	protected String lvField = DB.QUESTION_LV;
	protected String selectFieldA = DB.QUESTION_SELECTA;
	protected String selectFieldB = DB.QUESTION_SELECTB;
	protected String selectFieldC = DB.QUESTION_SELECTC;
	protected String selectFieldD = DB.QUESTION_SELECTD;
	

	/**
	 * Pass tableName parameter to create the question table for the specific subject.
	 * @param cmdName 
	 * @param tableName
	 */
	public AbstractQuestionCmd(String cmdName, String tableName) {
		super(cmdName);
		this.tableName = tableName;
	}

	@Override
	public void exec(Statement statement) throws Exception {
		// use dependent command
	}
		
}
