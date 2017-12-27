package org.imslab.sqlite.command;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsertCmd extends ModifyCommand {

	/**
	 * Statement primary field named "ID".
	 * All fields use type "TEXT"
	 */
	private String sqlTemplateBegin = "INSERT INTO %s (";
	private String sqlTemplateMid = " VALUES ("; 
	private String appendFieldTemplate = " \"%s\", ";
	private String appendFieldTemplateEnd = "\"%s\" )"; 
	
	private List<String> KeyList = null;
	private List<String> ValueList = null;
	
	/**
	 * Insert the data to the specific table.
	 * @param args    <Table> <Key1> <Value1> <Key2> <Value2>
	 */
	public InsertCmd(String... args) {
		super("CreateTable", args);
		KeyList = new ArrayList<>();
		ValueList = new ArrayList<>();
	}

	@Override
	public void exec(Statement statement) throws Exception {
		statement.executeUpdate(buildStatement());		
	}
	
	private void initKVList() {
		for(int i=1; i<args.size(); i++) {
			if (i%2!=0) {
				KeyList.add(args.get(i));
			}
			else {
				ValueList.add(args.get(i));
			}
		}
	}
	
	/**
	 * Helper function to create the SQL statement with args.
	 * @return
	 * @throws Exception
	 */
	private String buildStatement() throws Exception {
		if (args.size()<3) {
			throw new Exception("CreateTable command's args < 3");
		}
		if (args.size()%2==0) {
			throw new Exception("Wrong parameter's parity. It should be odd.");
		}
		
		// prepare kv list
		initKVList();
		
		String ret = String.format(sqlTemplateBegin, args.get(0));
		for(int i=0; i<KeyList.size()-1; i++) {
			ret += String.format(appendFieldTemplate, KeyList.get(i));
		}
		ret += String.format(appendFieldTemplateEnd, KeyList.get(KeyList.size()-1));

		ret += sqlTemplateMid;
		
		for(int i=0; i<ValueList.size()-1; i++) {
			ret += String.format(appendFieldTemplate, ValueList.get(i));
		}
		ret += String.format(appendFieldTemplateEnd, ValueList.get(ValueList.size()-1));
		
		ret += ";";
		
		
		// debug
		System.out.println(ret);
		return ret;
	}
	
}
