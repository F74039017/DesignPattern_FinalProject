package org.imslab.sqlite;


import java.sql.ResultSet;

import org.imslab.sqlite.command.Broker;
import org.imslab.sqlite.command.CreateAccountTableCmd;
import org.imslab.sqlite.command.CreateProblemTableCmd;

public class Main {

	public static void main( String args[] ) {
		Broker broker = new Broker();
//		broker.addCommand(new CreateTableCmd("IMSLAB", "FIELD1", "FIELD2")); // Facade
//		broker.addCommand(new InsertCmd("IMSLAB", "FIELD1", "HAPPY", "FIELD2", "DAY"));
//		broker.addCommand(new InsertCmd("IMSLAB", "FIELD1", "BAD", "FIELD2", "FEEL"));
		
		try {
			broker.addCommand(new CreateAccountTableCmd());
			broker.addCommand(new CreateProblemTableCmd());
		} catch (Exception e) {
			System.out.println("Account table created already");
		}

		ResultSet rs;
		try {
			broker.execMod();
			rs = broker.execQuery("IMSLAB");
			
			// print all result
			while(rs.next()) {
				String field1 = rs.getString("FIELD1");
				String field2 = rs.getString("FIELD2");
				System.out.println(String.format("%s %s", field1, field2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}

}
