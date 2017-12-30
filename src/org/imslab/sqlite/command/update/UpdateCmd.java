package org.imslab.sqlite.command.update;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imslab.sqlite.command.ModifyCommand;

public class UpdateCmd extends ModifyCommand {

	/**
	 * Statement primary field named "ID".
	 * All fields use type "TEXT"
	 */
	private String sqlTemplateBegin = "UPDATE %s SET ";
	private String appendFieldTemplate = "%s=\"%s\", ";
	private String appendFieldTemplateEnd = "%s=\"%s\" "; 
	private String whereTemplateBegin = " WHERE ";
	private String appendWhereFieldTemplate = "%s=\"%s\", ";
	private String appendWhereFieldTemplateEnd = "%s=\"%s\";";

	private List<String> updateKeyList = null;
	private List<String> updateValueList = null;
	private List<String> whereKeyList = null;
	private List<String> whereValueList = null;
	private boolean hasWhereClause = false;
	private int whereIndex = -1;
	
	/**
	 * Update fields from table.
	 * Where Table="FieldValue" follow the "--" annotation in form of <Where_table> <Where_field>.
	 * @param args    [Table] [Field] ... "--" [Where_Table] [Where_Field]
	 */
	public UpdateCmd(String... args) {
		super("UpdateCmd", args);
		updateKeyList = new ArrayList<>();
		updateValueList = new ArrayList<>();
		whereKeyList = new ArrayList<>();
		whereValueList = new ArrayList<>();
		whereIndex = this.args.indexOf("--");
		hasWhereClause = whereIndex==-1? false: true;
	}
	
	/**
	 * Digest set & where clause parameters.
	 * @throws Exception 
	 */
	private void initKVList() throws Exception {
		
		int i;
		// set clause
		for(i=1; i<whereIndex; i++) {
			if (i%2!=0) {
//				System.out.println("add update key: "+args.get(i));
				updateKeyList.add(args.get(i));
			}
			else {
//				System.out.println("add update value: "+args.get(i));
				updateValueList.add(args.get(i));
			}
		}
		
		
		// where clause
		if (!hasWhereClause) {
			return;
		}
	
		for(i=whereIndex+1; i<args.size(); i++) {
			if ((i-whereIndex)%2!=0) {
//				System.out.println("add where key: "+args.get(i));
				whereKeyList.add(args.get(i));
			}
			else {
//				System.out.println("add where value: "+args.get(i));
				whereValueList.add(args.get(i));
			}
		}
		
		if ((i-whereIndex)%2==0) {
			throw new Exception("UpdateMmd: Wrong number of where_clause's parameters.");
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
		if (args.size()<3) {
			throw new Exception("UpdateCmd need at least three arguments. <Table> <FieldName> <Value>");
		}
		
		// prepare where clause parameters.
		initKVList();
		
		int updateListSize = updateKeyList.size();
		int whereListSize = whereKeyList.size();
		
		String ret = String.format(sqlTemplateBegin, args.get(0));
		for(int i=0; i<updateListSize-1; i++) {
			ret += String.format(appendFieldTemplate, updateKeyList.get(i), updateValueList.get(i));
		}
		ret += String.format(appendFieldTemplateEnd, updateKeyList.get(updateListSize-1), updateValueList.get(updateListSize-1));
		
		if (hasWhereClause) {
			ret += whereTemplateBegin;
			for(int i=0; i<whereListSize-1; i++) {
				ret += String.format(appendWhereFieldTemplate, whereKeyList.get(i), whereValueList.get(i));
			}
		}
		ret += String.format(appendWhereFieldTemplateEnd, whereKeyList.get(whereListSize-1), whereValueList.get(whereListSize-1));
		
		// debug
		System.out.println(ret);
		return ret;
	}
	
}
