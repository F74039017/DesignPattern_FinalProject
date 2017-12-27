package org.imslab.sqlite.command;

import java.sql.Statement;

public interface ModifyExec {

	public void exec(Statement statement) throws Exception;
}
