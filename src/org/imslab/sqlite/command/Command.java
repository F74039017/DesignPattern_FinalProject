package org.imslab.sqlite.command;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {
	
	protected String name = null;
	protected String connStr = null;
	protected List<String> args = null;
	protected List<Command> dependentCmdList = null;
	
	private Command() {
		args = new ArrayList<>();
		dependentCmdList = new ArrayList<>();
	}
	
	private Command(String name) {
		this();
		this.name = name;
	}
	
	/**
	 * Construct instance with name and args
	 * @param name
	 * @param args
	 */
	public Command(String name, String... args) {
		this(name);
		for (String string : args) {
			this.args.add(string);
		}
	}
	
	/**
	 * Override this method to achieve the goal.
	 * @param statement
	 * @return
	 */
	abstract public void exec(Statement statement) throws Exception;
	
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
		exec(statement);
		
		// Reuse the statement object to faster the program.
		cascadingExec(statement);
		
		// close statement by root
		statement.close(); 
	}
	
	/**
	 * Execute composite commands by dfs.
	 * @param statement
	 */
	public void cascadingExec(Statement statement) throws Exception {
		for (Command command : dependentCmdList) {
			command.exec(statement);
			command.cascadingExec(statement);
		}
	}
	
	/**
	 * Add dependent commands and they will be executed by cascadingExec().
	 * @param command
	 */
	public void addDependentCmd(Command command) {
		dependentCmdList.add(command);
	}
	
	/* Accessors */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}
