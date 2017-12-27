package org.imslab.sqlite.command;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectCmd extends QueryCommand {

	private String sqlTemplateBegin = "SELECT ";
	private String appendFieldTemplate = "%s, "; // field
	private String appendFieldTemplateEnd = "%s FROM %s"; // field, tableName 
	private String whereTemplateBegin = " WHERE ";
	private String appendWhereFieldTemplate = "%s=\"%s\", ";
	private String appendWhereFieldTemplateEnd = "%s=\"%s\";";
	
	private List<String> KeyList = null;
	private List<String> ValueList = null;
	private boolean hasWhereClause = false;
	private int whereIndex = -1;
	
	/**
	 * Select fields from table.
	 * Where Table="FieldValue" follow the "--" annotation in form of <Where_table> <Where_field>.
	 * If only one parameter is passed to the constructor, then it will query all columns of the table.
	 * @param args    <Table> <Field> ... "--" <Where_Table> <Where_Field>
	 */
	public SelectCmd(String... args) {
		super("QueryCmd", args);
		KeyList = new ArrayList<>();
		ValueList = new ArrayList<>();
		whereIndex = this.args.indexOf("--");
		hasWhereClause = whereIndex==-1? false: true;
	}
	
	/**
	 * Digest where clause parameters.
	 * @throws Exception 
	 */
	private void initKVList() throws Exception {
		int i;
		for(i=whereIndex+1; i<args.size(); i++) {
			if ((i-whereIndex)%2!=0) {
				KeyList.add(args.get(i));
			}
			else {
				ValueList.add(args.get(i));
			}
		}
		
		if ((i-whereIndex)%2==0) {
			throw new Exception("SelectCmd: Wrong number of where_clause's parameters.");
		}
	}

	@Override
	public ResultSet exec(Statement statement) throws Exception {
		return statement.executeQuery(buildStatement());
	}
	
	/**
	 * Helper function to create the SQL statement with args.
	 * @return
	 * @throws Exception
	 */
	private String buildStatement() throws Exception {
		if (args.size()<1) {
			throw new Exception("QueryCmd need at least one argument for table name.");
		}
		
		// prepare where clause parameters.
		initKVList();
		
		int appendEndSize = whereIndex;
		
		// Query all columns
		if (!hasWhereClause && args.size() == 1) {
			args.add("*");
		} else if (hasWhereClause && appendEndSize==1) {
			args.add(1, "*");
			appendEndSize++;
		}
		
		String ret = sqlTemplateBegin;
		for(int i=1; i<appendEndSize-1; i++) {
			ret += String.format(appendFieldTemplate, args.get(i));
		}
		ret += String.format(appendFieldTemplateEnd, args.get(appendEndSize-1), args.get(0));
		
		if (hasWhereClause) {
			ret += whereTemplateBegin;
			for(int i=0; i<ValueList.size()-1; i++) {
				ret += String.format(appendWhereFieldTemplate, KeyList.get(i), ValueList.get(i));
			}
		}
		ret += String.format(appendWhereFieldTemplateEnd, KeyList.get(KeyList.size()-1), ValueList.get(ValueList.size()-1));
		
		// debug
		System.out.println(ret);
		return ret;
	}

}
