package org.imslab.sqlite.command.select;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imslab.sqlite.DB;
import org.imslab.sqlite.command.QueryCommand;

public class SelectSequenceCmd extends QueryCommand {

	protected String targetTableName = null;	
	protected String squenceTableName = DB.SQLITE_SEQUENCE_TABLENAME;
	protected String seqenceField = DB.SQLITE_SEQUENCE_SEQUENCE;
	protected String targetTableNameField = DB.SQLITE_SEQUENCE_NAME;
	
	protected String userName;

	/**
	 * Give target table name and return the auto_increment sequence of the table.
	 * @param targetTableName 
	 */
	public SelectSequenceCmd(String targetTableName) {
		super("SelectSequenceCmd", new String[0]);
		this.targetTableName = targetTableName;
	}
	
	@Override
	public ResultSet exec(Statement statement) throws Exception {
		List<String> paramList = new ArrayList<>();
		
		// select all columns
		paramList.add(squenceTableName);
		paramList.add(seqenceField); // get password
		paramList.add("--");
		paramList.add(targetTableNameField); // where userName is matched
		paramList.add(targetTableName);
		
		return (new SelectCmd(paramList.stream().toArray(String[]::new))).exec(statement);
	}

}
