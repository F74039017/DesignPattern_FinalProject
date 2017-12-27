package org.imslab.sqlite.command;

import java.sql.Statement;

public class CreateTableCmd extends ModifyCommand {

	/**
	 * Statement primary field named "ID".
	 * All fields use type "TEXT"
	 */
	private String sqlTemplateBegin = "CREATE TABLE %s " +
			            "(ID INTEGER PRIMARY KEY AUTOINCREMENT,";
	private String sqlTemplateEnd = " %s TEXT NOT NULL)"; 
	private String appendFieldTemplate = " %s TEXT NOT NULL,";
	
	/**
	 * Table's fields are defined by the constructor parameters.
	 * Once the instance created, it can't be modified.
	 * @param args    <Table> <Field1> <Field2>
	 */
	public CreateTableCmd(String... args) {
		super("CreateTable", args);
	}

	@Override
	public void exec(Statement statement) {
		try {
			statement.executeUpdate(buildStatement());		
		} catch (Exception e) {
			System.out.println(String.format("Table %s had already been created.", args.get(0)));
		}
	}
	
	/**
	 * Helper function to create the SQL statement with args.
	 * @return
	 * @throws Exception
	 */
	private String buildStatement() throws Exception {
		if (args.size()<2) {
			throw new Exception("CreateTable command's args < 2");
		}
		
		String ret = String.format(sqlTemplateBegin, args.get(0));
		for(int i=1; i<args.size()-1; i++) {
			//XXX: It would be better to use String Builder...
			ret += String.format(appendFieldTemplate, args.get(i));
		}
		ret += String.format(sqlTemplateEnd, args.get(args.size()-1));
		
		// debug
		System.out.println(ret);
		return ret;
	}
	
}
