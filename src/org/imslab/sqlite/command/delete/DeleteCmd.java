package org.imslab.sqlite.command.delete;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imslab.sqlite.command.ModifyCommand;

public class DeleteCmd extends ModifyCommand {

	private String sqlTemplateBegin = "DELETE FROM %s ";
	private String whereTemplateBegin = "WHERE ";
	private String appendWhereFieldTemplate = "%s=\"%s\", ";
	private String appendWhereFieldTemplateEnd = "%s=\"%s\";";
	
	private List<String> KeyList = null;
	private List<String> ValueList = null;
	
	/**
	 * Delete fields from table.
	 * @param args    <Table> <Where_Table> <Where_Field> ...
	 */
	public DeleteCmd(String... args) {
		super("DeleteCmd", args);
		KeyList = new ArrayList<>();
		ValueList = new ArrayList<>();
	}
	
	/**
	 * Digest where clause parameters.
	 * @throws Exception 
	 */
	private void initKVList() throws Exception {
		for(int i=1; i<args.size(); i++) {
			if (i%2!=0) {
				KeyList.add(args.get(i));
			}
			else {
				ValueList.add(args.get(i));
			}
		}
	}

	@Override
	public boolean exec(Statement statement) throws Exception {
		statement.executeUpdate(buildStatement());
		return true;
	}
	
	/**
	 * Helper function to create the SQL statement with args.
	 * @return
	 * @throws Exception
	 */
	private String buildStatement() throws Exception {
		if (args.size()<1) {
			throw new Exception("DeleteCmd need at least one argument for table name.");
		} else if (args.size()%2!=1) {
			throw new Exception("DeleteCmd: Wrong parity of arguments.");
		}
		
		// prepare where clause parameters.
		initKVList();
		
		String ret = String.format(sqlTemplateBegin, args.get(0));
		
		// Delete whole table
		if (args.size()==1) {
			ret += ";";
			// debug
			System.out.println(ret);
			
			return ret;
		}
		
		// Delete with where clause
		ret += whereTemplateBegin;
		for(int i=1; i<KeyList.size()-1; i++) {
			ret += String.format(appendWhereFieldTemplate, KeyList.get(i), ValueList.get(i));
		}
		ret += String.format(appendWhereFieldTemplateEnd, KeyList.get(KeyList.size()-1), ValueList.get(ValueList.size()-1));
		
		// debug
		System.out.println(ret);
		return ret;
	}

}
