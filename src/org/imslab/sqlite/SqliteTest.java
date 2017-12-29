package org.imslab.sqlite;

import java.util.HashMap;
import java.util.List;

import org.imslab.sqlite.command.*;

public class SqliteTest {

	public static void main( String args[] ) {
		Broker broker = new Broker();
		broker.addCommand(new CreateTableCmd("IMSLAB", "FIELD1", "FIELD2")); // Facade
		broker.addCommand(new InsertCmd("IMSLAB", "FIELD1", "P1", "FIELD2", "QQQ"));
		broker.addCommand(new InsertCmd("IMSLAB", "FIELD1", "P2", "FIELD2", "QQQ"));
		broker.addCommand(new CreateAccountTableCmd()); // Facade
		broker.addCommand(new RegisterCmd("Terry", "123456"));
		broker.addCommand(new CreateAccountTableCmd())
			  .addCommand(new CreateChineseQuestionTableCmd())
			  .addCommand(new CreateEnglishQuestionTableCmd())
			  .addCommand(new CreateMathQuestionTableCmd());
		broker.addCommand(new UpdateCmd("IMSLAB", "FIELD2", "WWW", "--", "FIELD1", "P2"));
		broker.addCommand(new DeleteCmd("IMSLAB"));

		List<HashMap<String, String>> rs;
		try {
			broker.execMod();
			rs = broker.execQuery(new SelectCmd("IMSLAB", "--", "FIELD2", "QQQ"));
			
			for(int i=0; i<rs.size(); i++) {
				HashMap<String, String> map = rs.get(i);
				System.out.println(String.format("%s %s %s", map.get("ID"), map.get("FIELD1"), map.get("FIELD2")));
			}
			
			// print all result
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}

}
