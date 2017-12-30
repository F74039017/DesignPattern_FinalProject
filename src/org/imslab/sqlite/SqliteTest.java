package org.imslab.sqlite;

import java.util.HashMap;
import java.util.List;

import org.imslab.question.Question;
import org.imslab.sqlite.command.*;
import org.imslab.sqlite.command.createTable.CreateAccountTableCmd;
import org.imslab.sqlite.command.createTable.CreateChineseQuestionTableCmd;
import org.imslab.sqlite.command.createTable.CreateEnglishQuestionTableCmd;
import org.imslab.sqlite.command.createTable.CreateMathQuestionTableCmd;
import org.imslab.sqlite.command.createTable.CreateTableCmd;
import org.imslab.sqlite.command.insert.InsertChineseQuestionCmd;
import org.imslab.sqlite.command.insert.InsertCmd;
import org.imslab.sqlite.command.insert.InsertEnglishQuestionCmd;
import org.imslab.sqlite.command.insert.InsertMathQuestionCmd;

public class SqliteTest {

	public static void main( String args[] ) {
		Broker broker = new Broker();
		
		// basic test
		broker.addCommand(new CreateTableCmd("IMSLAB", "FIELD1", "FIELD2")); // Facade
		broker.addCommand(new InsertCmd("IMSLAB", "FIELD1", "P1", "FIELD2", "QQQ"));
		broker.addCommand(new InsertCmd("IMSLAB", "FIELD1", "P2", "FIELD2", "QQQ"));
		broker.addCommand(new UpdateCmd("IMSLAB", "FIELD2", "WWW", "--", "FIELD1", "P2"));
		broker.addCommand(new DeleteCmd("IMSLAB"));
		
		// account test
		broker.addCommand(new CreateAccountTableCmd())
			  .addCommand(new RegisterCmd("Terry", "123456"));
		
		// question test
		broker.addCommand(new CreateChineseQuestionTableCmd())
			  .addCommand(new CreateEnglishQuestionTableCmd())
			  .addCommand(new CreateMathQuestionTableCmd());
		
		Question question = new Question.Builder().content("Hello World")
												 .lv("3")
												 .sa("a")
												 .sb("b")
												 .sc("c")
												 .sd("d")
												 .build();
		
		broker.addCommand(new InsertChineseQuestionCmd(question))
			  .addCommand(new InsertEnglishQuestionCmd(question))
			  .addCommand(new InsertMathQuestionCmd(question));
		
		List<HashMap<String, String>> rs;
		try {
			broker.execMod();
			
			// query test
			rs = broker.execQuery(new SelectCmd("IMSLAB", "--", "FIELD2", "QQQ"));
			
			for(int i=0; i<rs.size(); i++) {
				HashMap<String, String> map = rs.get(i);
				System.out.println(String.format("%s %s %s", map.get("ID"), map.get("FIELD1"), map.get("FIELD2")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}

}
