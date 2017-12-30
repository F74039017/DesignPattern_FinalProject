package org.imslab.sqlite.command;

import java.sql.Statement;

public interface ModifyExec {

	/**
	 * Define the SQL behavior interacting with database.
	 * @param statement
	 * @return false for stopping execute dependent commands.
	 * @throws Exception
	 */
	public boolean exec(Statement statement) throws Exception;
}
