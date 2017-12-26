package sqlite.command;

import java.sql.Statement;

public class CreateTableCmd extends Command {

	/**
	 * Statement primary field named "ID".
	 * All fields use type "TEXT"
	 */
	private String sqlTemplateBegin = "CREATE TABLE %s " +
			            "(ID INT PRIMARY KEY NOT NULL,";
	private String sqlTemplateEnd = " %s TEXT NOT NULL)"; 
	private String appendFieldTemplate = " %s TEXT NOT NULL,";
	
	/**
	 * Table's fields are defined by the constructor parameters.
	 * Once the instance created, it can't be modified.
	 * @param args    the first arg is table name, and the others are field names.
	 */
	public CreateTableCmd(String... args) {
		super("CreateTable", args);
	}

	@Override
	public void exec(Statement statement) throws Exception {
		statement.executeUpdate(buildStatement());		
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
		for(int i=1; i<args.size(); i++) {
			if (i!=args.size()-1) {
				//XXX: It would be better to use String Builder...
				ret = ret + String.format(appendFieldTemplate, args.get(i));
			}
			else {
				ret = ret + String.format(sqlTemplateEnd, args.get(args.size()-1));
			}
		}
		
		// debug
		System.out.println(ret);
		return ret;
	}
	
}
