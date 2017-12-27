package org.imslab.sqlite;


import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.imslab.sqlite.command.Broker;
import org.imslab.sqlite.command.CreateAccountTableCmd;
import org.imslab.sqlite.command.CreateProblemTableCmd;
import org.imslab.sqlite.command.CreateTableCmd;
import org.imslab.sqlite.command.InsertCmd;
import org.imslab.sqlite.command.RegisterCmd;
import org.imslab.sqlite.command.SelectCmd;

public class SqliteTest {

	public static void main( String args[] ) {
		Broker broker = new Broker();
		broker.addCommand(new CreateTableCmd("IMSLAB", "FIELD1", "FIELD2")); // Facade
		broker.addCommand(new InsertCmd("IMSLAB", "FIELD1", "BAD", "FIELD2", "GOOD"));
		broker.addCommand(new CreateAccountTableCmd()); // Facade
		broker.addCommand(new RegisterCmd("Terry", "123456"));
		
		try {
			broker.addCommand(new CreateAccountTableCmd());
			broker.addCommand(new CreateProblemTableCmd());
		} catch (Exception e) {
			System.out.println("Account table created already");
		}

		List<HashMap<String, String>> rs;
		try {
			broker.execMod();
			rs = broker.execQuery(new SelectCmd("IMSLAB", "--", "FIELD1", "BAD"));
			
			for(int i=0; i<rs.size(); i++) {
				HashMap<String, String> map = rs.get(i);
				System.out.println(String.format("%s %s", map.get("FIELD1"), map.get("FIELD2")));
			}
			
			// print all result
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}

}
