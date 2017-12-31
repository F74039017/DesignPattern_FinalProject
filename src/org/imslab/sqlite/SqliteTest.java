package org.imslab.sqlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.imslab.Model;
import org.imslab.question.ChineseQuestionCmdFactory;
import org.imslab.question.Question;
import org.imslab.question.QuestionCmdFactory;
import org.imslab.sqlite.command.*;
import org.imslab.sqlite.command.createTable.*;
import org.imslab.sqlite.command.delete.*;
import org.imslab.sqlite.command.insert.*;
import org.imslab.sqlite.command.select.*;
import org.imslab.sqlite.command.update.*;

public class SqliteTest {

	public static void main( String args[] ) {
		
		System.out.println(Model.getInstance().getNextAutoIncrementId("haha"));
			
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
		
		Model model = Model.getInstance();
		try {
			System.out.println(model.checkPassword("Terry", "123456"));
			System.out.println(model.checkPassword("Terry", "234567"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		
		QuestionCmdFactory factory = new ChineseQuestionCmdFactory();
		broker.addCommand(factory.getInsertQuestionCmd(question));
		
		question = new Question.Builder().content("Hello World")
				 .id("1")
				 .lv("2")
				 .sa("aa")
				 .sb("bb")
				 .sc("cc")
				 .sd("dd")
				 .build();
		broker.addCommand(new UpdateChineseQuestionCmd(question));
		
		List<HashMap<String, String>> rs;
		try {
			broker.execMod();
			
			// query test
			List<String> lvList = new ArrayList<>();
			lvList.add("3");
			lvList.add("5");
			rs = broker.execQuery(new SelectChineseQuestionCmd(lvList));
			
			for(int i=0; i<rs.size(); i++) {
				HashMap<String, String> map = rs.get(i);
				System.out.println(String.format("%s %s %s", map.get("ID"), map.get("SA"), map.get("SB")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}

}
