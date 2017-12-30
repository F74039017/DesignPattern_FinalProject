package org.imslab.sqlite.command;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class ModifyCommand extends Command implements ModifyExec {

	protected List<ModifyCommand> dependentCmdList = null;
	
	public ModifyCommand(String name) {
		this(name, new String[0]); // zero size array
	}
	
	public ModifyCommand(String name, String[] args) {
		super(name, args);
		dependentCmdList = new ArrayList<>();
	}

	/**
	 * Override this method to achieve the goal.
	 * @param statement
	 * @return
	 */
	abstract public boolean exec(Statement statement) throws Exception;
	
	/**
	 * Create the statement from the connection and exec(),
	 * After exec(), execute the dependent command if exists.
	 * Called by broker.
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public void rootExec(Connection conn) throws Exception {
		// check whether the connection is null?
		if(conn==null)
			throw new Exception("Null Connection");
		
		Statement statement = conn.createStatement();
		if (exec(statement)) {
			// Reuse the statement object to faster the program.
			cascadingExec(statement);			
		}
		
		// close statement by root
		statement.close(); 
	}
	
	/**
	 * Execute composite commands by dfs.
	 * @param statement
	 */
	public void cascadingExec(Statement statement) throws Exception {
		for (ModifyCommand command : dependentCmdList) {
			command.exec(statement);
			command.cascadingExec(statement);
		}
	}
	
	/**
	 * Add dependent commands and they will be executed by cascadingExec().
	 * @param command
	 */
	public void addDependentCmd(ModifyCommand command) {
		dependentCmdList.add(command);
	}
}
