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
import org.imslab.sqlite.command.select.SelectPasswordCmd;
import org.imslab.sqlite.command.select.SelectSequenceCmd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	
	/* Common data */
	private String userName = "Unknown";
	private Broker broker = new Broker();
	// ModifyDBController set it for targeting database table
	private String currentModQuestionTable = DB.CHINESE_TABLENAME; 
	
	private ObservableList<Question> chineseQuestionList = FXCollections.observableArrayList();
	private ObservableList<Question> englishQuestionList = FXCollections.observableArrayList();
	private ObservableList<Question> mathQuestionList = FXCollections.observableArrayList();
	
	/* Singleton pattern */
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
			broker.addCommand(QuestionCmdFactory.getFactoryByTableName(question.getSubjectTable())
					.getInsertQuestionCmd(question)).execMod();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void updateQuestion(Question question) throws Exception {
		try {
			broker.addCommand(QuestionCmdFactory.getFactoryByTableName(question.getSubjectTable())
					.getUpdateQuestionCmd(question)).execMod();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void deleteQuestion(Question question) throws Exception {
		try {
			broker.addCommand(QuestionCmdFactory.getFactoryByTableName(question.getSubjectTable())
					.getDeleteQuestionCmd(question)).execMod();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public List<Question> selectQuestion(String subjectTable, List<String> lvList) throws Exception {
		try {
			List<HashMap<String, String>> rs =  broker.execQuery(QuestionCmdFactory.getFactoryByTableName(subjectTable)
					.getSelectQuestionCmd(lvList));
			return Question.digestSqlResult(rs, subjectTable);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			// this won't be executed.
			return null;
		}
	}

	
	/**
	 * Give table name to get the next insert id.
	 * @param cmd
	 * @return  next insert id of the table.
	 */
	public String getNextAutoIncrementId(String tableName) {
		try {
			List<HashMap<String, String>> rs;
			rs = broker.execQuery(new SelectSequenceCmd(tableName));
			if (rs.size()==0) {
				throw new Exception("No "+ tableName +" table ?\nEmpty result of the sequence query.");
			}
			return String.valueOf(Integer.parseInt(rs.get(0).get(DB.SQLITE_SEQUENCE_SEQUENCE))+1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			// this won't be returned.
			return null;
		}
	}
	
	/* Accessors */
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ObservableList<Question> getChineseQuestionList() {
		return chineseQuestionList;
	}

	public ObservableList<Question> getEnglishQuestionList() {
		return englishQuestionList;
	}

	public ObservableList<Question> getMathQuestionList() {
		return mathQuestionList;
	}

	public String getCurrentModQuestionTable() {
		return currentModQuestionTable;
	}

	public void setCurrentModQuestionTable(String currentModQuestionTable) {
		// debug
		System.out.println("Target question table " + currentModQuestionTable);
		this.currentModQuestionTable = currentModQuestionTable;
	}

}
