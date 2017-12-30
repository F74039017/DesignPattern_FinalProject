package org.imslab;

import java.util.HashMap;
import java.util.List;

import org.imslab.question.Question;
import org.imslab.question.QuestionCmdFactory;
import org.imslab.sqlite.DB;
import org.imslab.sqlite.command.Broker;
import org.imslab.sqlite.command.RegisterCmd;
import org.imslab.sqlite.command.createTable.CreateAccountTableCmd;
import org.imslab.sqlite.command.createTable.CreateChineseQuestionTableCmd;
import org.imslab.sqlite.command.createTable.CreateEnglishQuestionTableCmd;
import org.imslab.sqlite.command.createTable.CreateMathQuestionTableCmd;
import org.imslab.sqlite.command.select.SelectPassword;

public class Model {
	
	// Common data
	private String userName = "Unknown";
	private Broker broker = new Broker();
	
	// Singleton pattern
	private static Model model = null;
		
	private Model() {
		initTable();
		
		// Default user
		try {
			broker.addCommand(new RegisterCmd(DB.ACCOUNT_DEFAULT_USERNAME, DB.ACCOUNT_DEFAULT_PASSWORD)).execMod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Model getInstance() {
		if (model == null) {
			model = new Model();
		}
		return model;
	}
	
	private void initTable() {
		broker.addCommand(new CreateAccountTableCmd())
			  .addCommand(new CreateChineseQuestionTableCmd())
			  .addCommand(new CreateEnglishQuestionTableCmd())
			  .addCommand(new CreateMathQuestionTableCmd());
		try {
			broker.execMod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Model function */
	
	public void register(String name, String password) throws Exception {
		broker.addCommand(new RegisterCmd(name, password)).execMod();
	}
	
	public boolean checkPassword(String name, String password) throws Exception {
		List<HashMap<String, String>> rs = broker.execQuery(new SelectPassword(name));
		if (rs.size()==0) {
			return false;
		}
		String md5Code = rs.get(0).get(DB.ACCOUNT_PASSWORD);
//		System.out.println(String.format("%s password check %s %s", name, RegisterCmd.md5(password), md5Code));
		return RegisterCmd.md5(password).equals(md5Code);
	}
	
	public void addQuestion(QuestionCmdFactory factory, Question question) throws Exception {
		broker.addCommand(factory.getInsertQuestionCmd(question)).execMod();
	}
	
	public void updateQuestion(QuestionCmdFactory factory, Question question) throws Exception {
		broker.addCommand(factory.getUpdateQuestionCmd(question)).execMod();
	}
	
	public void deleteQuestion(QuestionCmdFactory factory, Question question) throws Exception {
		broker.addCommand(factory.getDeleteQuestionCmd(question)).execMod();
	}
	
	public List<HashMap<String, String>> selectQuestion(QuestionCmdFactory factory, List<String> lvList) throws Exception {
		return broker.execQuery(factory.getSelectQuestionCmd(lvList));
	}
	
	/* Accessors */
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
