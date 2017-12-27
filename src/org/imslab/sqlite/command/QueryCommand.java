package org.imslab.sqlite.command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class QueryCommand extends Command implements QueryExec {

	
	public QueryCommand(String name, String[] args) {
		super(name, args);
	}
	
	/**
	 * Create the statement from the connection and exec(),
	 * Called by broker.
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> query(Connection conn) throws Exception {
		// check whether the connection is null?
		if(conn==null)
			throw new Exception("Null Connection");
		
		Statement statement = conn.createStatement();
		ResultSet resultSet = exec(statement);
		
		// convert resultSet to HashMap in Arraylist
		List<HashMap<String, String>> ret = convertResultSetToList(resultSet);
		
		// close statement by root
		statement.close(); 
		
		return ret;
	}
	
	private List<HashMap<String, String>> convertResultSetToList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

	    while (rs.next()) {
	        HashMap<String, String> row = new HashMap<String, String>(columns);
	        for(int i=1; i<=columns; ++i) {
	            row.put(md.getColumnName(i), rs.getString(i));
	        }
	        list.add(row);
	    }

	    return list;
	}
	
}
