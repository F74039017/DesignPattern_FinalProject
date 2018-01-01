package org.imslab;

import java.util.HashMap;
import java.util.List;

import org.imslab.question.Question;
import org.imslab.sqlite.DB;
import org.imslab.sqlite.command.Broker;
import org.imslab.sqlite.command.RegisterCmd;
import org.imslab.sqlite.command.createTable.CreateAccountTableCmd;
import org.imslab.sqlite.command.select.SelectPasswordCmd;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Model {
	
	/* Common data */
	private String userName = "Unknown";
	private Broker broker = new Broker();
	
	// ModifyDB
	private SubjectData currentData = null;
	private ChineseData chineseData = null;
	private EnglishData englishData = null;
	private MathData mathData = null;
	
	/* Singleton pattern */
	private static Model model = null;
		
	private Model() {
		init();
		
		// Default user
		try {
			broker.addCommand(new RegisterCmd(DB.ACCOUNT_DEFAULT_USERNAME, DB.ACCOUNT_DEFAULT_PASSWORD)).execMod();
		} catch (RegisterCmd.AlreadyRegisteredException e) {
			// nothing to do.
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
	
	private void init() {
		broker.addCommand(new CreateAccountTableCmd());
		try {
			broker.execMod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Database function */
	
	public boolean register(String name, String password) throws Exception {
		try {
			broker.addCommand(new RegisterCmd(name, password)).execMod();
			return true;
		} catch (Exception e) {
			// rethrow exception
			throw e;
		} 
	}
	
	public boolean checkPassword(String name, String password) throws Exception {
		List<HashMap<String, String>> rs = broker.execQuery(new SelectPasswordCmd(name));
		if (rs.size()==0) {
			return false;
		}
		String md5Code = rs.get(0).get(DB.ACCOUNT_PASSWORD);
//		System.out.println(String.format("%s password check %s %s", name, RegisterCmd.md5(password), md5Code));
		return RegisterCmd.md5(password).equals(md5Code);
	}
	
	public void addQuestion(Question question) {
		try {
			broker.addCommand(currentData.getFactory().getInsertQuestionCmd(question)).execMod();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void updateQuestion(Question question) throws Exception {
		try {
			broker.addCommand(currentData.getFactory().getUpdateQuestionCmd(question)).execMod();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void deleteQuestion(Question question) throws Exception {
		try {
			broker.addCommand(currentData.getFactory().getDeleteQuestionCmd(question)).execMod();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public List<Question> selectQuestion(List<String> lvList) throws Exception {
		try {
			List<HashMap<String, String>> rs =  broker.execQuery(currentData.getFactory().getSelectQuestionCmd(lvList));
			return Question.digestSqlResult(rs, currentData.getSubjectTableName());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			// this won't be executed.
			return null;
		}
	}
	
	/* Popup dialog */
	
	private void showDialog(Alert alert, String title, String content, String header) {
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
	}
	
	public void alert(String title, String content, String header) {
		Alert alert = new Alert(AlertType.WARNING);
		showDialog(alert, title, content, header);
	}
	
	public void alert(String title, String content) {
		alert(title, content, null);
	}
	
	public void info(String title, String content, String header) {
		Alert alert = new Alert(AlertType.INFORMATION);
		showDialog(alert, title, content, header);
	}
	
	public void info(String title, String content) {
		info(title, content, null);
	}
	
	/* Accessors */
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public SubjectData getCurrentData() {
		return currentData;
	}

	public void setCurrentData(SubjectData currentData) {
		this.currentData = currentData;
	}

	public ChineseData getChineseData() {
		return chineseData;
	}

	public void setChineseData(ChineseData chineseData) {
		this.chineseData = chineseData;
	}

	public EnglishData getEnglishData() {
		return englishData;
	}

	public void setEnglishData(EnglishData englishData) {
		this.englishData = englishData;
	}

	public MathData getMathData() {
		return mathData;
	}

	public void setMathData(MathData mathData) {
		this.mathData = mathData;
	}

}
