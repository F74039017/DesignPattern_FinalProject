package org.imslab.sqlite.command.select;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imslab.sqlite.command.QueryCommand;

public class SelectCmd extends QueryCommand {

	private String sqlTemplateBegin = "SELECT ";
	private String appendFieldTemplate = "%s, "; // field
	private String appendFieldTemplateEnd = "%s FROM %s"; // field, tableName 
	private String whereTemplateBegin = " WHERE ";
	private String appendWhereFieldTemplate = "%s=\"%s\" OR ";
	private String appendWhereFieldTemplateEnd = "%s=\"%s\";";
	
	private List<String> KeyList = null;
	private List<String> ValueList = null;
	private boolean hasWhereClause = false;
	private int whereIndex = -1;
	
	/**
	 * Select fields from table.
	 * Notice: Where clause supports 'OR' operator only.
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
		if (!hasWhereClause) {
			return;
		}
		
		int i;
		for(i=whereIndex+1; i<args.size(); i++) {
			if ((i-whereIndex)%2!=0) {
				KeyList.add(args.get(i));
			}
			else {
				ValueList.add(args.get(i));
			}
		}
		
		if ((i-whereIndex)%2==0 || whereIndex==args.size()-1) {
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
		
		int appendEndIndex;
		
		if (hasWhereClause) {
			if (whereIndex == 1) {
				// Query all columns
				args.add(1, "*");
				appendEndIndex = 2;
			} else {
				appendEndIndex = whereIndex;
			}
		} else {
			if (args.size() ==1) {
				args.add("*");
				appendEndIndex = 2;
			} else {
				appendEndIndex = args.size();
			}
		}
		
		String ret = sqlTemplateBegin;
		for(int i=1; i<appendEndIndex-1; i++) {
			ret += String.format(appendFieldTemplate, args.get(i));
		}
		ret += String.format(appendFieldTemplateEnd, args.get(appendEndIndex-1), args.get(0));
		
		if (hasWhereClause) {
			ret += whereTemplateBegin;
			for(int i=0; i<ValueList.size()-1; i++) {
				ret += String.format(appendWhereFieldTemplate, KeyList.get(i), ValueList.get(i));
			}
			ret += String.format(appendWhereFieldTemplateEnd, KeyList.get(KeyList.size()-1), ValueList.get(ValueList.size()-1));
		}
		
		// debug
		System.out.println(ret);
		return ret;
	}

}
