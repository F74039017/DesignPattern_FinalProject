package org.imslab.sqlite.command;

import java.sql.ResultSet;
import java.sql.Statement;

public interface QueryExec {

	public ResultSet exec(Statement statement) throws Exception;
}
